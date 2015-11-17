# StockDemo

```
[cloudera@quickstart Stock]$ hadoop  fs -mkdir /user/cloudera/stock/ 
[cloudera@quickstart Stock]$ hadoop  fs -mkdir /user/cloudera/stock/input
[cloudera@quickstart Stock]$ hadoop fs -put stock /user/cloudera/stock/input/1
[cloudera@quickstart Stock]$ hadoop  fs -mkdir /user/cloudera/stock/output
[cloudera@quickstart Stock]$ hadoop jar target/Stock-0.0.1-SNAPSHOT.jar Demo.StockPrice /user/cloudera/stock/input/1 /user/cloudera/stock/output/1
```
```
15/11/16 23:50:11 INFO client.RMProxy: Connecting to ResourceManager at /0.0.0.0:8032
15/11/16 23:50:11 WARN mapreduce.JobSubmitter: Hadoop command-line option parsing not performed. Implement the Tool interface and execute your application with ToolRunner to remedy this.
15/11/16 23:50:12 INFO input.FileInputFormat: Total input paths to process : 1
15/11/16 23:50:12 INFO mapreduce.JobSubmitter: number of splits:1
15/11/16 23:50:12 INFO mapreduce.JobSubmitter: Submitting tokens for job: job_1447745402181_0001
15/11/16 23:50:13 INFO impl.YarnClientImpl: Submitted application application_1447745402181_0001
15/11/16 23:50:13 INFO mapreduce.Job: The url to track the job: http://quickstart.cloudera:8088/proxy/application_1447745402181_0001/
15/11/16 23:50:13 INFO mapreduce.Job: Running job: job_1447745402181_0001
15/11/16 23:50:26 INFO mapreduce.Job: Job job_1447745402181_0001 running in uber mode : false
15/11/16 23:50:26 INFO mapreduce.Job:  map 0% reduce 0%
15/11/16 23:50:33 INFO mapreduce.Job:  map 100% reduce 0%
15/11/16 23:50:44 INFO mapreduce.Job:  map 100% reduce 100%
15/11/16 23:50:44 INFO mapreduce.Job: Job job_1447745402181_0001 completed successfully
15/11/16 23:50:44 INFO mapreduce.Job: Counters: 49
	File System Counters
		FILE: Number of bytes read=474
		FILE: Number of bytes written=222741
		FILE: Number of read operations=0
		FILE: Number of large read operations=0
		FILE: Number of write operations=0
		HDFS: Number of bytes read=475
		HDFS: Number of bytes written=40
		HDFS: Number of read operations=6
		HDFS: Number of large read operations=0
		HDFS: Number of write operations=2
	Job Counters 
		Launched map tasks=1
		Launched reduce tasks=1
		Data-local map tasks=1
		Total time spent by all maps in occupied slots (ms)=5911
		Total time spent by all reduces in occupied slots (ms)=6204
		Total time spent by all map tasks (ms)=5911
		Total time spent by all reduce tasks (ms)=6204
		Total vcore-seconds taken by all map tasks=5911
		Total vcore-seconds taken by all reduce tasks=6204
		Total megabyte-seconds taken by all map tasks=6052864
		Total megabyte-seconds taken by all reduce tasks=6352896
	Map-Reduce Framework
		Map input records=44
		Map output records=44
		Map output bytes=380
		Map output materialized bytes=474
		Input split bytes=124
		Combine input records=0
		Combine output records=0
		Reduce input groups=5
		Reduce shuffle bytes=474
		Reduce input records=44
		Reduce output records=5
		Spilled Records=88
		Shuffled Maps =1
		Failed Shuffles=0
		Merged Map outputs=1
		GC time elapsed (ms)=145
		CPU time spent (ms)=1150
		Physical memory (bytes) snapshot=412610560
		Virtual memory (bytes) snapshot=3007467520
		Total committed heap usage (bytes)=410914816
	Shuffle Errors
		BAD_ID=0
		CONNECTION=0
		IO_ERROR=0
		WRONG_LENGTH=0
		WRONG_MAP=0
		WRONG_REDUCE=0
	File Input Format Counters 
		Bytes Read=351
	File Output Format Counters 
		Bytes Written=40
```

```
[cloudera@quickstart Stock]$ hadoop  fs -cat /user/cloudera/stock/output/1/*
Baba	89
Goog	646
Tsla	298
VZ	52
Yhoo	48

```
