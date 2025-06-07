package com.cryptotweets;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class HashtagReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    private IntWritable result = new IntWritable();
    private int minFrequency;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        minFrequency = context.getConfiguration().getInt("hashtag.min.frequency", 1);
    }

    @Override
    public void reduce(Text key, Iterable<IntWritable> values, Context context)
            throws IOException, InterruptedException {

        int sum = 0;
        for (IntWritable val : values) {
            sum += val.get();
        }

        if (sum >= minFrequency) {
            result.set(sum);
            context.write(key, result);
        }
    }
}
