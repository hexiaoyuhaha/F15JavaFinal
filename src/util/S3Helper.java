package util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import java.io.IOException;
import java.util.ArrayList;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
//import util.*;
public class S3Helper {
	private final String bucketName = "f15.java.team.project";
	AmazonS3 s3client;
	
	public S3Helper() {
		BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAIE5JYSJPIS44UITA", "oep3o0+IDvjMLR28MtNnE8BMcuE5hjUkWTMJJxJz");
		s3client = new AmazonS3Client(awsCreds);

//        s3client = new AmazonS3Client(new ProfileCredentialsProvider());	
	}
	
	public ArrayList<String> getListOfFileName() throws IOException {
		ArrayList<String> names = new ArrayList<String>();
		System.out.println("Listing objects");
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest()
			.withBucketName(bucketName);
		ObjectListing objectListing;            
		do {
			objectListing = s3client.listObjects(listObjectsRequest);
			for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
				names.add(objectSummary.getKey());
				System.out.println("[S3]List: " + objectSummary.getKey());
			}
			listObjectsRequest.setMarker(objectListing.getNextMarker());
		} while (objectListing.isTruncated());
		return names;
    }
	
	public void upLoadFile(String uploadFileName) throws IOException {
		System.out.println("Uploading a new object to S3 from a file\n");
		File file = new File(uploadFileName);
		s3client.putObject(new PutObjectRequest(bucketName, uploadFileName, file));
    }
		
	public void downLoadFile(String downloadFileName) throws IOException {
		System.out.println("Downloading a new object" + downloadFileName);
                S3Object object = s3client.getObject(
                  new GetObjectRequest(bucketName, downloadFileName));
		InputStream objectData = object.getObjectContent();
		// Process the objectData stream.
		File file = new File(downloadFileName);      
		OutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
		int read = -1;
		while ( ( read = objectData.read() ) != -1 ) {
			writer.write(read);
		}
		writer.flush();
		writer.close();
		objectData.close();
    }
	
	public void deleteFile(String fileName) {
		s3client.deleteObject(new DeleteObjectRequest(bucketName, fileName));
	}
	
	public static void main(String[] args) throws IOException {
		S3Helper s3Helper = new S3Helper();
		s3Helper.getListOfFileName();
		s3Helper.upLoadFile("upload_test_file.txt");
		s3Helper.getListOfFileName();
		s3Helper.deleteFile("upload_test_file.txt");
		s3Helper.getListOfFileName();
		//s3Helper.downLoadFile("upload_test_file.txt");
	}
}