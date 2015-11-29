package Demo;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;


public class StockPrice {

		public static class StockMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

			public void map(LongWritable key, Text value, Context context)throws IOException, InterruptedException {

				String record = value.toString();
				String[] parts = record.split(",");
				context.write(new Text(parts[0]), new IntWritable(Integer.parseInt(parts[1])));
			}
		}

		public static class StockReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

			public void reduce(Text key, Iterable<IntWritable> values,Context context) throws IOException, InterruptedException {

				int maxValue = Integer.MIN_VALUE;
				int sum = 0;
				int avg = 0, count = 0;
				//Looping and calculating Max for each year
				for (IntWritable val : values) {
					maxValue = Math.max(maxValue, val.get());
					//sum += val.get();
					//count++;
				}
				//avg = sum / count;
				context.write(key, new IntWritable(maxValue));
			}
		}

		public static void main(String[] args) throws Exception {
			// for running on cluster
			//Configuration conf = new Configuration();
			//Job job = Job.getInstance(conf, "Stock Price");
			
			args[0] = "/home/cloudera/workspace/Stock/stock";
			args[1] = "/home/cloudera/workspace/Stock/output/output1.txt";

			
			Job job = new Job();
			job.setJobName("StockPrice");
			
			job.setJarByClass(StockPrice.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(IntWritable.class);

			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(IntWritable.class);

			job.setMapperClass(StockMapper.class);
			job.setReducerClass(StockReducer.class);

			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(TextOutputFormat.class);

			FileInputFormat.addInputPath(job, new Path(args[0]));
			FileOutputFormat.setOutputPath(job,new Path(args[1]));

			job.waitForCompletion(true);

		}
}
