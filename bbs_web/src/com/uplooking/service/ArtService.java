package com.uplooking.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.uplooking.pojo.Art;
import com.uplooking.pojo.Ext;
import com.uplooking.pojo.Word;
import com.uplooking.util.Pager;

public interface ArtService {
	List<Word> getWordList(String key) throws Exception;
	Pager getArtList(String key,int size,String url) throws Exception;
	Pager getArtList(String key,int index,int size,String url) throws Exception;
	Map<String, Object> getArt(String id) throws Exception;
	Map<String,Object> addRpt(String aid,String uid,String msg) throws Exception;
	Pager getRptList(String aid,int index,int size,String url) throws Exception;
	Map<String,Object> artUpload(Art art,MultipartFile[] files) throws Exception;
	Ext extDownload(String id) throws Exception;
}
