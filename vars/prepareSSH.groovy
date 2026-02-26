def call(String host) {

    echo "Preparing SSH for ${host}"

    sh """
        ssh-keyscan -t rsa,dsa ${host} >> ~/.ssh/known_hosts 2>/dev/null || true
    """
}
