import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;


public class Test {

	public static void main(String[] args) {
//		double f = 0.5555;
//		DecimalFormat df = new DecimalFormat("#0000.00");
//		System.out.println(df.format(f));


		String path = "./data/汇总/2015/12/180100240341.xlsx";
		Path path1 = Paths.get(path);
		System.out.println(path1.toAbsolutePath());
	}
}
