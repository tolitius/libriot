#!/bin/sh

function sign {
  kSecret=$(printf "AWS4$1" | xxd -p -c 256)
  kDate=$(printf "$2" | openssl dgst -binary -sha256 -hmac -macopt hexkey:$kSecret | xxd -p -c 256)
  kRegion=$(printf "$3" | openssl dgst -binary -sha256 -hmac -macopt hexkey:$kDate | xxd -p -c 256)
  kService=$(printf "$4" | openssl dgst -binary -sha256 -hmac -macopt hexkey:$kRegion | xxd -p -c 256)
  kSigning=$(printf "aws4_request" | openssl dgst -binary -sha256 -hmac -macopt hexkey:$kService | xxd -p -c 256)
  signedString=$(printf "$5" | openssl dgst -binary -hex -sha256 -hmac -macopt hexkey:$kSigning | sed 's/^.* //')
  $(printf $signedString > $6)
}

sign $1 $2 $3 $4 $5 $6
