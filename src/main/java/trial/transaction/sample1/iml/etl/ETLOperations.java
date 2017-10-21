package trial.transaction.sample1.iml.etl;

import lombok.Getter;

public enum ETLOperations {

	GET_HDFS_FILES("GetHdfsFiles"),
	GET_NEW_SCHEMA("GetNewSchema"),
	UPDAT_NEW_SCHEMA("UpdateNewSchema"),
	LOAD_TO_HIVEHDFS("LoadToHiveHDFS"),
	UPDATE_HDFS_FILELIST("UpdateHdfsFilelist");

	@Getter
	String name;

	ETLOperations(String name) {
		this.name = name;
	}

}
