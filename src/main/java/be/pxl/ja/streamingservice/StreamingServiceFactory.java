package be.pxl.ja.streamingservice;

import be.pxl.ja.streamingservice.exception.DuplicateEmailException;

public class StreamingServiceFactory {
	private static StreamingService streamingService;

	static {
		try {
			streamingService = new StreamingService();
		} catch (DuplicateEmailException e) {
			e.printStackTrace();
		}
	}

	public static StreamingService getStreamingService() {
		return streamingService;
	}

}
