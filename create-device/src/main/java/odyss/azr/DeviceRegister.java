package odyss.azr;

import com.microsoft.azure.iot.service.exceptions.IotHubException;
import com.microsoft.azure.iot.service.sdk.Device;
import com.microsoft.azure.iot.service.sdk.DeviceStatus;
import com.microsoft.azure.iot.service.sdk.RegistryManager;

import java.io.IOException;
import java.net.URISyntaxException;


public class DeviceRegister {
	
	private static final String connectionString = "HostName=odyss-iot.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=lFQKHNR0dzw3grXvgpJRq6rCrIk8RTBkYpM8bfMLtjM=";
	private static final String deviceId = "odyss-dvc";

	
	public static void main( String[] args ) throws IOException, URISyntaxException, Exception {
		
		RegistryManager registryManager = RegistryManager.createFromConnectionString(connectionString);

		Device device = Device.createFromId(deviceId, null, null);
		try {
		  device = registryManager.addDevice(device);
		  System.out.println("Device CREATED");
		} catch (IotHubException iote) {
		  try {
		    device = registryManager.getDevice(deviceId);
		    System.out.println("Device FOUND");
		  } catch (IotHubException iotf) {
		    iotf.printStackTrace();
		  }
		}
		System.out.println("Device id     : " + device.getDeviceId());
		System.out.println("Device status : " + device.getStatus());
		System.out.println("Device key    : " + device.getPrimaryKey());
		
		device.setStatus(DeviceStatus.Enabled);
		registryManager.updateDevice(device);
		
		
		
		System.out.println("DONE!");
	}
	
	
}
