#! /usr/bin/expect -f

spawn ./oids-master/bin/oid.sh -f snmp.yaml

sleep 3

ANSWER=$(send -- ".1.3.6.1.6.3.1.1.5\r")

echo $ANSWER

expect {
"*.1.3.6.1.6.3.1.1.5: false" {send -- "test failed\r"}
"*.1.3.6.1.6.3.1.1.5: true" {send -- "test succeed\r"}
}

send -- "quit\r"
