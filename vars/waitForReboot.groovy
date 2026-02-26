def call(String remoteHost, String logFile) {

    echo "Waiting for server to come back: ${remoteHost}"

    sh """
echo "Waiting for ${remoteHost} to reboot..." | tee -a ${logFile}

for i in \$(seq 1 60); do
    echo "Attempt \$i to check SSH..." | tee -a ${logFile}
    if ssh -o StrictHostKeyChecking=no -o ConnectTimeout=10 ${remoteHost} 'echo Server is back'; then
        echo "Server is back online." | tee -a ${logFile}
        exit 0
    fi
    echo "Retrying in 10 seconds..." | tee -a ${logFile}
    sleep 10
done

echo "ERROR: Server did not come back after waiting." | tee -a ${logFile}
exit 1
    """
}
