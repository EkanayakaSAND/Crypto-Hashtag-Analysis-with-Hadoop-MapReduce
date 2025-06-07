package com.cryptotweets;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class HashtagAnalysis {

    public static void main(String[] args) throws Exception {
        System.out.println("Args length: " + args.length);
        for (int i = 0; i < args.length; i++) {
            System.out.println("Arg[" + i + "]: " + args[i]);
        }

        if (args.length != 3) {
            System.err.println("Usage: HashtagAnalysis <some-arg> <input path> <output path>");
            System.exit(-1);
        }

        Configuration conf = new Configuration();
        conf.setInt("hashtag.min.frequency", 2);

        Job job = Job.getInstance(conf, "crypto hashtag analysis");
        job.setJarByClass(HashtagAnalysis.class);

        job.setMapperClass(HashTagMapper.class);

        job.setCombinerClass(HashtagReducer.class);
        job.setReducerClass(HashtagReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // args[1] is input path, args[2] is output path
        FileInputFormat.addInputPath(job, new Path(args[1]));
        FileOutputFormat.setOutputPath(job, new Path(args[2]));

        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}
