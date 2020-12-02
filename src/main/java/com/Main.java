package com;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectResult;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String accessKey = "";
        String secretKey = "";
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);

        File file = new File("src\\main\\resources\\text.txt");
        AmazonS3 client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion("us-east-2")
                .build();

        String bucketName = "test-bucket-serg-local";
        if (!client.doesBucketExist(bucketName)) {
            client.createBucket(bucketName);
        }

        PutObjectResult putObjectResult = client.putObject(bucketName, "hello.txt", file);

        client.deleteObject(bucketName, "hello.txt");

    }
}
