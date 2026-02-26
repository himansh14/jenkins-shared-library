def call(String remoteHost, String logFile) {

    echo "Updating and rebooting ${remoteHost}"

    sh """
        echo "Stage: Update, Upgrade and Reboot" | tee -a ${logFile}

        ssh -o StrictHostKeyChecking=no ${remoteHost} "bash -s" << 'SYSUPDATE' | tee -a ${logFile}
        set +e
        sudo apt-get update -y || true
        sudo apt-get upgrade -y || true
        echo "System update completed"
        nohup sudo bash -c 'sleep 5 && reboot' >/dev/null 2>&1 &
        echo "Reboot initiated"
SYSUPDATE
    """
}
