def call(String remoteHost, String logFile) {

    echo "Cleaning server logs on ${remoteHost}"

    sh """
ssh ${remoteHost} "sudo bash -s" << 'CLEAN' | tee -a ${logFile}
echo "Cleaning server logs"
journalctl --vacuum-time=1d || true
apt-get autoremove -y || true
rm -f /var/log/*.gz || true
find /var/log -type f -size +100M -exec truncate -s 0 {} \\; || true
echo "Log cleanup completed"
CLEAN
    """
}
