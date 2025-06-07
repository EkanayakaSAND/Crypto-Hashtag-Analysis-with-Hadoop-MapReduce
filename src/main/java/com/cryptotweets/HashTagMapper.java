package com.cryptotweets;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class HashTagMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    private final static IntWritable one = new IntWritable(1);
    private Text hashtag = new Text();

    // Regex to match hashtags: '#' followed by letters, digits, or underscores
    private static final Pattern HASHTAG_PATTERN = Pattern.compile("#[\\w_]+");

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {

        String line = value.toString().toLowerCase();

        // Extract hashtags
        Matcher matcher = HASHTAG_PATTERN.matcher(line);

        while (matcher.find()) {
            hashtag.set(matcher.group());
            context.write(hashtag, one);
        }
    }
}
