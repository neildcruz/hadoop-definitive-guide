import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

/**
 * 
 */

/**
 * @author neildcruz
 *
 */
public class FileCopyProgress {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		String localDataSrc = args[0];
		String destination = args[1];
		
		InputStream in = new BufferedInputStream(new FileInputStream(localDataSrc));
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(URI.create(destination), conf);
		OutputStream out = fs.create(new Path(args[1]), new Progressable() {
			public void progress() {
				System.out.print(".");
			}
		});
		
		IOUtils.copyBytes(in, out, 4096, true);
	}

}
