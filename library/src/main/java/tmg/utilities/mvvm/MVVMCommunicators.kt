package tmg.utilities.mvvm

interface MVVMActivityToFragmentCommunicator {
    /**
     * Delegate method to notify [MVVMFragment] that a back button has been pressed on [MVVMActivity]
     * Return true if the fragment has handled the back press. False otherwise
     */
    fun activityFragmentBackClicked(): Boolean
}

interface MVVMFragmentToActivityCommunicator {
    /**
     * Provider for the communicator link between [MVVMActivity] -> [MVVMFragment]
     * (done like this to work through fragment attach events, so link is rebuild everytime 
     */
    fun provideCommunicator(communicator: MVVMActivityToFragmentCommunicator)
}