def call(String logFile, String branchName) {

    echo "Initializing log file..."

    sh """
        echo "===================================" | tee -a ${logFile}
        echo "Starting CD Pipeline" | tee -a ${logFile}
        echo "Deploying Branch: ${branchName}" | tee -a ${logFile}
        echo "===================================" | tee -a ${logFile}
    """
