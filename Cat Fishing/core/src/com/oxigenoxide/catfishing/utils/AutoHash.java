/*******************************************************
 * Copyright (C) 2015 Mirco Timmermann - All Rights Reserved
 * 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Mirco Timmermann <mtimmermann@gmx.de>, December 2016
 * 
 *******************************************************/
package com.oxigenoxide.catfishing.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.List;


/*
 * used for auto-recognizing changed/user edited resource files. For example, images.
 */
public class AutoHash {
	private String _path = "";
	private String _fileExtension = "";
	private String _hashSavePath = "";
	
	
	public interface IAutoHasher {
		void OnCreate();
	}
	
	
	public AutoHash(String path, IAutoHasher autoHasher) {
		this(path, "png", autoHasher);
	}
	
	public AutoHash(String path, String fileExtension, IAutoHasher autoHasher) {
		this(path, fileExtension, autoHasher, path);
	}
	
	public AutoHash(String path, String fileExtension, IAutoHasher autoHasher, String optinalHashSavePath) {
		_path = path;
		_fileExtension = fileExtension;
		_hashSavePath = optinalHashSavePath;

		File file = new File(_path);
		System.out.println("AutoHasha " + file);
		if(file.isDirectory()) {
			System.out.println("Hashaauto " + file);
			if(autoHasher != null) {
				if(needRebuild()) {
					autoHasher.OnCreate();
					WriteHashToHashFile();
				}
			}
		}
	}
	
	private void WriteHashToHashFile() {
		String hash = createHash();
		if(hash != null && hash != "") {
			WriteHashToFile(hash);
		}
	}
	
	private void WriteHashToFile(String hash) {
		String hashUrl = _path + "/" + "auto.hash";
		if(_hashSavePath != null && _hashSavePath != "") {
			hashUrl = _hashSavePath + "/" + "auto.hash";
		}
		
		File hashFile = new File(hashUrl);
		boolean exists = hashFile.exists();
		boolean ok = false;
		if(!exists) {
			boolean created;
			try {
				created = hashFile.createNewFile();
				if(created) {
					ok = true;
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			ok = true;
		}
		
		if(ok) {
			BufferedWriter output;
			try {
				output = new BufferedWriter(new FileWriter(hashFile));
				output.write(hash);
				output.close();
		            
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getHash() {
		String hashUrl = _path + "/" + "auto.hash";
		if(_hashSavePath != null && _hashSavePath != "") {
			hashUrl = _hashSavePath + "/" + "auto.hash";
		}
		
		String hash = "";
		File hashFile = new File(hashUrl);
		if(hashFile.exists()) { //&& !f.isDirectory()) { /* do something */ }
			if(!hashFile.isDirectory()) {
				List<String> lines;
				try {
					lines = Files.readAllLines(Paths.get(hashUrl), Charset.forName("UTF-8"));
					for(String line : lines) {
						hash += line;
					}
				
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return hash;
	}
	
	public String MD5(String md5) {
		try {
			MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			
			return sb.toString();
			
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private String createHash() {
		File[] files = new File(_path).listFiles();
		
		String totalHash = "";
		for(File file : files) {
		    if(file.isFile()) {
		    	String name = file.getName();
		    	int index = name.lastIndexOf(".");
		    	String extension = "";
		    	if(index > 0) {
		    	    extension = name.substring(index+1);
		    	    if(extension.equals(_fileExtension)) {
		    	    	long length = file.length();
		    	    	long lastModified = file.lastModified();
		    	    	
		    	    	String hash_uncode = name + "" + length + "" + lastModified;
		    	    	totalHash = MD5(totalHash + hash_uncode);
		    	    }
		    	}
		    }
		}
		
		return totalHash;
	}
	
	private boolean needRebuild() {
		String ref_hash = getHash();
		String hash = createHash();
		
		boolean rebuild = !ref_hash.equals(hash);
		return rebuild;
	}
}
