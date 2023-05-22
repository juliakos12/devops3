URL="http://localhost"

FAILURE_CODES="^[45][0-9][0-9]$"
function log_result {
    local result=$1

    echo "$result" >> /var/log/check_web_server.log
}

response=$(curl --write-out %{http_code} --silent --output /dev/null $URL)

if ! [[ $response =~ $FAILURE_CODES ]]; then
    log_result "Web server is up and running, response code: $response"
else
    log_result "Web server is down, response code: $response"
fi