package Demo;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
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

			//Looping and calculating Max for each year
			for (IntWritable val : values) {
				maxValue = Math.max(maxValue, val.get());
				}

			context.write(key, new IntWritable(maxValue));
			}
		}

		public static void main(String[] args) throws Exception {

			Configuration conf = new Configuration();

			Job job = new Job(conf, "StockPrice");
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
