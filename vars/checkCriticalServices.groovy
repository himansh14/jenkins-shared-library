def call(String remoteHost, String logFile) {

    echo "Checking critical services on ${remoteHost}"

    sh """
ssh ${remoteHost} "sudo bash -s" << 'CHECK' | tee -a ${logFile}
set -e
echo "Checking critical services"
for svc in site24x7monagent.service ds_agent.service vls_agent.service; do
    if systemctl is-active --quiet "\$svc"; then
        echo "Service running: \$svc"
    else
        echo "Service NOT running. Starting: \$svc"
        systemctl start "\$svc"
        echo "Service started: \$svc"
    fi
done
echo "Critical services check completed"
CHECK
    """
}
