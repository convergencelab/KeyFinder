package com.example.smartdrone;

import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Determine which key has the strongest relationship to the notes being received.
 * Notes are added to a list when received, and removed from the when they have become inactive.
 * The active key is the key with the strongest strength. If more than one key has max strength then
 * the active key will be chosen at random.
 */
public class KeyFinder {
    /**
     * Default length of note timer.
     * Timer removes note from active note list.
     */
    private static final int DEFAULT_NOTE_TIMER_LEN = 2;

    /**
     * Default length of key timer.
     * Timer sets key as active key.
     */
    private static final int DEFAULT_KEY_TIMER_LEN = 3;

    /**
     * A list of all active notes.
     * Active notes affect key strength.
     */
    private LinkedList<Note> _activeNotes;

    /**
     * Collection of all notes;
     * ie: Chromatic Scale
     */
    private NoteCollection _allNotes;

    /**
     * Object that contains all key objects.
     */
    private KeyCollection _allKeys;

    /**
     * The max strength among all keys.
     * Not necessarily the active key.
     */
    private int _maxStrength;

    /**
     * The key that has the strongest correlation with the active notes.
     */
    private Key _activeKey;

    /**
     * Number of seconds for the note timer task.
     */
    private int _noteTimerLength;

    /**
     * Number of seconds for the key timer task.
     */
    private int _keyTimerLength;

    /**
     * Flag for a note being removed the active note list.
     */
    private boolean _noteHasBeenRemoved;

    /**
     * The last note that was removed.
     */
    private Note _removedNote;

    /**
     * Flag for a checking if the active key has changed.
     */
    private boolean _activeKeyHasChanged;

    /**
     * Array stores the scheduled removal of notes.
     * Index with null means there is no scheduled removal.
     */
    private ScheduledFuture<?>[] _scheduledNoteTasks;

    /**
     * Array stares the scheduled update of active key.
     * Index with null value means there is no task scheduled.
     */
    private ScheduledFuture<?>[] _scheduledKeyTasks;

    /**
     * Thread pool for note tasks.
     */
    private ScheduledThreadPoolExecutor _noteTaskPool;

    /**
     * Thread pool for key tasks.
     */
    private ScheduledThreadPoolExecutor _keyTaskPool;

    /**
     * Status of contender for active key.
     */
    private boolean[] _isContender;

    /**
     * Strength of key in relation to active note list.
     * Strength is incremented by one for every active note found in key.
     */
    private int[] _keyStrengths;

    /**
     * Constructor.
     */
    public KeyFinder() {
        _activeNotes = new LinkedList<>();
        _allNotes = new NoteCollection();
        _allKeys = new KeyCollection(_allNotes);
        _activeKey = null;

        _noteTimerLength = DEFAULT_NOTE_TIMER_LEN;
        _keyTimerLength = DEFAULT_KEY_TIMER_LEN;

        _noteHasBeenRemoved = false;
        _activeKeyHasChanged = false;

        _noteTaskPool = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(MusicTheory.TOTAL_NOTES); //todo can this be replaced by a lower integer?
        _noteTaskPool.setRemoveOnCancelPolicy(true); //todo: not exactly sure what this should be but it seems to work
        _scheduledNoteTasks = new ScheduledFuture<?>[MusicTheory.TOTAL_NOTES];

        _keyTaskPool = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(7); // todo: extract constant. 7 should be most timers running at one time.
        _keyTaskPool.setRemoveOnCancelPolicy(true);
        _scheduledKeyTasks = new ScheduledFuture<?>[MusicTheory.TOTAL_NOTES];

        _isContender = new boolean[MusicTheory.TOTAL_NOTES];
        _keyStrengths = new int[MusicTheory.TOTAL_NOTES];
    }

    /**
     * Returns the List of active notes.
     * @return      LinkedList; all active notes.
     *
     * @deprecated list should not be accessed directly outside of KeyFinder class.
     */
    @Deprecated
    public LinkedList<Note> getActiveNotes() {
        return this._activeNotes;
    }

    /**
     * Get already constructed note at given index.
     * Index of -1 returns null.
     * @param       ix int; index of target note.
     * @return      Note; target note.
     */
    public Note getNote(int ix) {
        return _allNotes.getNoteAtIndex(ix);
    }

    /**
     * Gets string of active notes.
     * User should not be able to access the list directly.
     * @return      String; string of active notes.
     */
    public String getActiveNotesString() {
        return _activeNotes.toString();
    }

    /**
     * Return number of notes in active note list.
     * @return      int; size of active note list.
     */
    public int getActiveNoteListSize() {
        return _activeNotes.size();
    }

    /**
     * Check if active note list contains target note.
     * @param       targetNote Note; note to check.
     * @return      boolean; true if active note list contains target note.
     */
    public boolean activeNotesContain(Note targetNote) {
        return this._activeNotes.contains(targetNote);
    }

    /**
     * Make call to method with Note parameter.
     * @param       targetIx int; index of target note.
     * @return      boolean; true if list contains note.
     */
    public boolean activeNotesContain(int targetIx) {
        return activeNotesContain(getNote(targetIx));
    }

    /**
     * Add notes to active notes.
     * Updates key strengths.
     * @param       targetNote Note; note to be added to active notes.
     */
    public void addNoteToList(Note targetNote) {
        // If note in list.
        if (activeNotesContain(targetNote)) {
            // Do nothing.
            // Android app restarts timer.
            // Could remove it and add it back,
            // But that would only slightly improve readability for programmer,
            // and create more operations for the application.
            return;
        }
        // If note not in list.
        else {
            // Add to list.
            this._activeNotes.add(targetNote);
            incrementKeysWithNote(targetNote);
//            updateMaxStrength();
            _maxStrength = findMaxStrength();
            updateContenderKeys();
        }
    }

    /**
     * Make call to method with Note parameter.
     * @param       ix int; index of note.
     */
    public void addNoteToList(int ix) {
        addNoteToList(_allNotes.getNoteAtIndex(ix));
    }

    /**
     * Removes notes from active notes.
     * Updates key strengths.
     * @param       targetNote Note; note to be removed from active notes.
     */
    public void removeNoteFromList(Note targetNote) {
        // If note not in _activeNotes.
        if (!activeNotesContain(targetNote)) {
            return;
        }
        // Remove note from _activeNotes.
        else {
            this._activeNotes.remove(targetNote);
            // Flag note removed. Used for debugging.
            _noteHasBeenRemoved = true;
            _removedNote = targetNote;
            // Decrement strength of all keys containing this note.
            decrementKeysWithNote(targetNote);
//            updateMaxStrength();
            _maxStrength = findMaxStrength();
            updateContenderKeys();
        }
    }

    /**
     * Make call to method with Note parameter.
     * @param       noteIx int; index of note to remove.
     */
    public void removeNoteFromList(int noteIx) {
        removeNoteFromList(_allNotes.getNoteAtIndex(noteIx));
    }

    /**
     * Returns key collection object.
     * @return      KeyCollection; object containing all keys.
     * @deprecated use {@link #getMajorKey(int)} instead.
     */
    @Deprecated
    public KeyCollection getAllKeys() {
        return this._allKeys;
    }

    /**
     * Returns major key matching given index.
     * @param       keyIx int; index of key.
     * @return      Key; key matching index.
     */
    public Key getMajorKey(int keyIx) {
        return _allKeys.getMajorKeyAtIndex(keyIx);
    }

    /**
     * Increment strength of all keys containing target note.
     * @param       target Note; target note.
     */
    private void incrementKeysWithNote(Note target) {
        int curKeyOffset;
        int targetNoteIx = target.getIx();
        // For each key that contains note.
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) { // DIATONIC_SCALE_SIZE = 7
            curKeyOffset = MusicTheory.PHRYGIAN_SCALE_SEQUENCE[i];
            // Increment keys strength.
            _allKeys.getMajorKeyAtIndex(
                    (targetNoteIx + curKeyOffset) % MusicTheory.TOTAL_NOTES).incrementStrength(); // TOTAL_NOTES = 12
        }
    }

    /**
     * Decrement strength of all keys containing target note.
     * @param       target Note; target note.
     */
    private void decrementKeysWithNote(Note target) {
        int curKeyOffset;
        int targetNoteIx = target.getIx();
        // For each key that contains note.
        for (int i = 0; i < MusicTheory.DIATONIC_SCALE_SIZE; i++) { // DIATONIC_SCALE_SIZE = 7
            curKeyOffset = MusicTheory.PHRYGIAN_SCALE_SEQUENCE[i];
            // Decrement keys strength.
            _allKeys.getMajorKeyAtIndex(
                    (targetNoteIx + curKeyOffset) % MusicTheory.TOTAL_NOTES).decrementStrength(); // TOTAL_NOTES = 12
        }
    }

    /**
     * Get strength of key.
     * @param       keyIx int; index of key.
     * @return      int; strength of key.
     */
    private int getKeyStrength(int keyIx) {
        return _keyStrengths[keyIx];
    }

    /**
     * Get strength of key.
     * @param       key Key; key.
     * @return      int; strength of key.
     */
    private int getKeyStrength(Key key) {
        return getKeyStrength(key.getIx());
    }

    /**
     * Increment strength of key.
     * @param       keyIx int; index of key to increment.
     */
    private void incrementKeyStrength(int keyIx) {
        _keyStrengths[keyIx]++;
    }

    /**
     * Increment strength of key.
     * @param       toIncrement Key; key to increment.
     */
    private void incrementKeyStrength(Key toIncrement) {
        incrementKeyStrength(toIncrement.getIx());
    }

    /**
     * Decrement strength of key.
     * @param       keyIx int; index of key to decrement.
     */
    private void decrementStrength(int keyIx) {
        _keyStrengths[keyIx]--;
    }

    /**
     * Decrement strength of key.
     * @param       toDecrement Key; key to decrement.
     */
    private void decrementStrength(Key toDecrement) {
        decrementStrength(toDecrement.getIx());
    }

    /**
     * Checks all keys; returns max strength.
     * @return      int; max strength among all keys.
     */
    private int findMaxStrength() {
        int maxStrength = 0;
        Key curKey;
        // For each key.
        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) {
            curKey = this._allKeys.getMajorKeyAtIndex(i);
            // If it's greater than current max.
            if (curKey.getStrength() > maxStrength) {
                // Update max.
                maxStrength = curKey.getStrength();
            }
        }
        return maxStrength;
    }

    /**
     * Return max strength of all keys.
     * @return      int; max strength.
     */
    public int getMaxStrength() {
        return _maxStrength;
    }

    /**
     * Returns the active key.
     */
    public Key getActiveKey() {
        return this._activeKey;
    }

    /**
     * Set the active key.
     * Triggers flag for active key change.
     * @param       newActiveKey Key; new active key.
     */
    public void setActiveKey(Key newActiveKey) {
        this._activeKey = newActiveKey;
        _activeKeyHasChanged = true;
    }

    /**
     * Get active keys strength.
     * No active key returns 0.
     * @return      int; active keys strength.
     */
    private int getActiveKeyStrength() {
        if (_activeKey == null) {
            return 0;
        }
        return _allKeys.getMajorKeyAtIndex(_activeKey.getIx()).getStrength();
    }

    /**
     * Monitors keys that are in contention for becoming the new active key.
     */
    private void updateContenderKeys() {
        Key curKey;
        // For each key.
        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) {
//            curKey = getAllKeys().getMajorKeyAtIndex(i);
            curKey = getMajorKey(i);
            // Don't check active key.
            if (curKey != _activeKey) {
                // There are 4 states an inactive key can be in,
                // however, only two require action:

                // 1. Key is contender and doesn't meet the requirements.
                if (curKey.isContender() && !meetsContenderRequirements(curKey)) {
                    // Cancel timer.
                    curKey.cancelKeyTimer();
                    // Not a contender.
                    curKey.setIsContender(false);
                }
                // 2. Key is not a contender and meets the requirements.
                else if (!curKey.isContender() && meetsContenderRequirements(curKey)) {
                    // Start timer.
                    curKey.startKeyTimer(this, _keyTimerLength);
                    // Is a contender.
                    curKey.setIsContender(true);
                }
            }
        }
    }

    /**
     * An inactive key is a contender if it meets two requirements:
     *   1. Key has greater strength than the current active key.
     *   2. Key has max strength (can be same as other inactive keys).
     * @param       curKey Key; key to be monitored.
     * @return      boolean; true if key meets requirements.
     */
    private boolean meetsContenderRequirements(Key curKey) {
        return curKey.getStrength() > getActiveKeyStrength() && curKey.getStrength() == _maxStrength;
    }

    /**
     * Get the length of the note timer.
     * @return      int; number of seconds.
     */
    public int getNoteTimerLength() {
        return _noteTimerLength;
    }

    /**
     * Set the length of the note timer.
     * @param       seconds int; number of seconds.
     */
    public void setNoteTimerLength(int seconds) {
        _noteTimerLength = seconds;
    }

    /**
     * Get the length of the key timer.
     * @return      int; number of seconds.
     */
    public int getKeyTimerLength() {
        return _keyTimerLength;
    }

    /**
     * Set the length of the key timer.
     * @param       seconds int; number of seconds.
     */
    public void setKeyTimerLength(int seconds) {
        _keyTimerLength = seconds;
    }

    /**
     * Check if note has been removed.
     * @return      boolean; true if note has been removed.
     */
    public boolean getNoteHasBeenRemoved() {
        return _noteHasBeenRemoved;
    }

    /**
     * Reset the flag for note being removed.
     * @param       status boolean; if note has been removed.
     */
    public void setNoteHasBeenRemoved(boolean status) {
        _noteHasBeenRemoved = status;
    }

    /**
     * Get the last removed note.
     * @return      Note; last removed note.
     */
    public Note getRemovedNote() {
        return _removedNote;
    }

    /**
     * Check if active key has changed.
     * @return      boolean; true if active key has changed.
     */
    public boolean getActiveKeyHasChanged() {
        return _activeKeyHasChanged;
    }

    /**
     * Set active key change flag.
     * @param       status boolean; true if active key has changed.
     */
    public void setActiveKeyHasChanged(boolean status) {
        _activeKeyHasChanged = status;
    }

    /**
     * Clears all active notes and resets all key strengths.
     */
    public void cleanse() {
        // Cancel all active timers for Keys and Notes.
        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) {
            _allKeys.getMajorKeyAtIndex(i).cancelKeyTimer();
            if (noteIsScheduled(i)) {
                cancelNoteRemoval(i);
            }
        }
        // Remove each note from list. Function takes care of managing key strengths.
        while (!_activeNotes.isEmpty()) {
            removeNoteFromList(_activeNotes.peekFirst());
        }
        _activeKey = null;
        _noteHasBeenRemoved = false;
        _activeKeyHasChanged = false;
    }

    // todo will have to create new method here; also should be a private function
    /**
     * Cancel all the key timers.
     * Function used when key has been changed to prevent bug.
     */
    public void cancelAllKeyTimers() {
        Key curKey;
        for (int i = 0; i < MusicTheory.TOTAL_NOTES; i++) {
//            curKey = getAllKeys().getMajorKeyAtIndex(i);
            curKey = getMajorKey(i);
            // Only contender keys will have an active timer.
            if (curKey.isContender()) {
                curKey.cancelKeyTimer();
                curKey.setIsContender(false);
            }
        }
    }

    /**
     * Schedule removal of note from active note list.
     * @param       toSchedule Note; note to schedule.
     */
    public void scheduleNoteRemoval(Note toSchedule) {
        Runnable noteRemoval = new Runnable() {
            @Override
            public void run() {
                removeNoteFromList(toSchedule);
            }
        };
        _scheduledNoteTasks[toSchedule.getIx()] = _noteTaskPool.schedule(noteRemoval, _noteTimerLength, TimeUnit.SECONDS);
    }

    /**
     * Schedule removal of note from active note list.
     * @param       ix int; index of note.
     */
    public void scheduleNoteRemoval(int ix) {
        scheduleNoteRemoval(_allNotes.getNoteAtIndex(ix));
    }

    /**
     * Cancels scheduled removal of note.
     * @param       toCancel Note; note to cancel.
     */
    public void cancelNoteRemoval(Note toCancel) {
        _scheduledNoteTasks[toCancel.getIx()].cancel(true);
        _scheduledNoteTasks[toCancel.getIx()] = null;
    }

    /**
     * Cancels scheduled removal of note.
     * @param       ix int; index of note.
     */
    public void cancelNoteRemoval(int ix) {
        cancelNoteRemoval(_allNotes.getNoteAtIndex(ix));
    }

    /**
     * Get number of current active note threads.
     * @return      int; number of current active note treads.
     */
    public int getNoteThreadCount() {
        return _noteTaskPool.getActiveCount();
    }

    /**
     * Check if note has a removal scheduled.
     * @param       targetNote Note; target note.
     * @return      boolean; true if removal scheduled.
     */
    public boolean noteIsScheduled(Note targetNote) {
        // Removal is scheduled.
        return _scheduledNoteTasks[targetNote.getIx()] != null;
    }

    /**
     * Check if note has a removal scheduled.
     * @param       targetIx; index of target note.
     * @return      boolean; true if removal scheduled.
     */
    public boolean noteIsScheduled(int targetIx) {
        return noteIsScheduled(_allNotes.getNoteAtIndex(targetIx));
    }

    /**
     * Schedule active key change.
     * @param       toSchedule Key; key to become active key.
     */
    private void scheduleActiveKeyChange(Key toSchedule) {
        Runnable activeKeyChange = new Runnable() {
            @Override
            public void run() {
                setActiveKey(toSchedule);
            }
        };
        _scheduledKeyTasks[toSchedule.getIx()] = _keyTaskPool.schedule(activeKeyChange, _keyTimerLength, TimeUnit.SECONDS);
    }

    /**
     * Schedule active key change.
     * @param       keyIx int; index of key to become active key.
     */
    private void scheduleActiveKeyChange(int keyIx) {
        scheduleActiveKeyChange(_allKeys.getMajorKeyAtIndex(keyIx));
    }

    /**
     * Cancel scheduled change of active key.
     * @param       toCancel Key; key to cancel.
     */
    private void cancelActiveKeyChange(Key toCancel) {
        _scheduledKeyTasks[toCancel.getIx()].cancel(true);
        _scheduledKeyTasks[toCancel.getIx()] = null;
    }

    /**
     * Cancel scheduled change of active key.
     * @param       keyIx int; index of key to cancel.
     */
    private void cancelActiveKeyChange(int keyIx) {
        _scheduledKeyTasks[keyIx].cancel(true);
        _scheduledKeyTasks[keyIx] = null;
    }

    /**
     * Check if key is active key contender.
     * @return      boolean; true if contender.
     */
    private boolean isContender(Key toCheck) {
        return _isContender[toCheck.getIx()];
    }

    /**
     * Check if key is active key contender.
     * @return      boolean; true if contender.
     */
    private boolean isContender(int toCheckIx) {
        return _isContender[toCheckIx];
    }

    /**
     * Set status of key contender.
     * @param       key Key; key to set.
     * @param       status boolean; true if contender.
     */
    private void setContenderStatus(Key key, boolean status) {
        _isContender[key.getIx()] = status;
    }

    /**
     * Set status of key contender.
     * @param       keyIx int; index of key to set.
     * @param       status boolean; true if contender.
     */
    private void setContenderStatus(int keyIx, boolean status) {
        _isContender[keyIx] = status;
    }


}
