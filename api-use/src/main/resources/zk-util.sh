#!/bin/sh
array=('172.16.101.50' '192.168.147.129' '192.168.147.131')
path="/usr/local/zookeeper/bin/"
for var in ${array[*]}
do
        process_count=$(ssh $var 'ps -ef|grep zookeeper |grep -v grep |wc -l')
        if [ "$process_count" -eq 1 ];then
                echo -e "($var)的zookeeper服务正常"

                res1=$(ssh $var 'sh $pathzkServer.sh status | grep -w follower | wc -l')
                if [[ $res1 -eq 1 ]];then
                        echo "($var)是从节点"
                else
                        echo "($var)是主节点"
                fi

        else
                echo -e "($var)的zookeeper服务未启动"
        fi
done