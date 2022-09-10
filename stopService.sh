for pid in `ps aux | grep FACCAT-G2-SEGURANCA | awk '{print $2}'`
do
	kill $pid
done
