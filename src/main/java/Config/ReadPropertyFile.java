package Config;

import org.aeonbits.owner.Config;


@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"file:./src/main/java/Config/config.properties"})


public interface ReadPropertyFile extends Config {

	String env();

	String weather_web_url();

	String weather_api_url();

}