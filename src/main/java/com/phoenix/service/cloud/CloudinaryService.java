package com.phoenix.service.cloud;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface CloudinaryService {

    Map<?,?> upload(File file, Map<?,?> params) throws IOException;
}
