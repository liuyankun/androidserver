package myspring;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.TableConfiguration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class MapperGenerator {

	public static void main(String[] args) {
		try {
			List<String> warnings = new ArrayList<String>();
			   boolean overwrite = true;
			   File configFile = new File("test\\generatorConfig.xml");
			   ConfigurationParser cp = new ConfigurationParser(warnings);
			   Configuration config = cp.parseConfiguration(configFile);			 
			   DefaultShellCallback callback = new DefaultShellCallback(overwrite);
			   MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
			   String path = System.getProperty("user.dir")+"\\WebContent\\WEB-INF\\lib\\mysql-connector-java-5.1.38-bin.jar";
			   config.addClasspathEntry(path);
			   myBatisGenerator.generate(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
