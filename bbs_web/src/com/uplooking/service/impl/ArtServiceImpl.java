package com.uplooking.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.uplooking.dao.ArtMapper;
import com.uplooking.dao.ExtMapper;
import com.uplooking.dao.RptMapper;
import com.uplooking.dao.WordMapper;
import com.uplooking.pojo.Art;
import com.uplooking.pojo.Ext;
import com.uplooking.pojo.Rpt;
import com.uplooking.pojo.Word;
import com.uplooking.service.ArtService;
import com.uplooking.util.Pager;

@Service("artService")
public class ArtServiceImpl implements ArtService {

	private Map<String, Object> result =null;
	
	@Autowired
	@Qualifier("wordMapper")
	private WordMapper wordMapper;
	
	@Autowired
	@Qualifier("artMapper")
	private ArtMapper artMapper;
	
	@Autowired
	@Qualifier("extMapper")
	private ExtMapper extMapper;
	
	@Autowired
	@Qualifier("rptMapper")
	private RptMapper rptMapper;
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public List<Word> getWordList(String key) throws Exception {
		return wordMapper.find(key);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Pager getArtList(String key, int size, String url) throws Exception {
		//隐藏业务
		if(wordMapper.exist(key)==0){
			wordMapper.insert(key);
		}else{
			wordMapper.update(key);
		}
		return new Pager(1, artMapper.count(key), size, url, 
				artMapper.find(key, 0, size));
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Pager getArtList(String key, int index, int size, String url) throws Exception {
		return new Pager(index, artMapper.count(key), size, url, 
				artMapper.find(key, (index-1)*size, size));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> getArt(String id) throws Exception {
		result = new HashMap<String, Object>();
		//点击次数累加
		artMapper.update(id);
		Art art = artMapper.findById(id);
		//关联附件
		List<Ext> exts = extMapper.findByAid(id);
		//关键回复
		List<Rpt> rpts = rptMapper.find(id, 0, 10);
		result.put("art", art);
		result.put("exts", exts);
		result.put("rpts", new Pager(1, rptMapper.count(id), 10, "", rpts));
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> addRpt(String aid, String uid, String msg) throws Exception {
		result = new HashMap<String, Object>();
		Rpt rpt = new Rpt();
		rpt.setOaid(aid);
		rpt.setOdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		rpt.setOfid(rptMapper.maxFloor(aid)+"");
		rpt.setOmessage(msg);
		rpt.setOuid(uid);
		if(rptMapper.insert(rpt)==1){
			result.put("code", 200);
			result.put("message", "回复成功");
		}else{
			result.put("code", 406);
			result.put("message", "回复失败");
		}
		return result;
	}
	
	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Pager getRptList(String aid, int index, int size, String url) throws Exception {
		return new Pager(index, rptMapper.count(aid), size, url, 
				rptMapper.find(aid, (index-1)*size, size));
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Map<String, Object> artUpload(Art art, MultipartFile[] files) throws Exception {
		result = new HashMap<String, Object>();
		art.setAclick(0);
		art.setAdate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		if(artMapper.insert(art)==1){
			for(MultipartFile file:files){
				if(!file.isEmpty()){
					Ext ext = new Ext();
					ext.setEaid(art.getAid());
					ext.setEname(file.getOriginalFilename());
					ext.setEpath("F:"+File.separatorChar+"tmp"+File.separatorChar);
					ext.setEsize(file.getSize());
					ext.setEtype(file.getContentType());
					ext.setEsuffix(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
					extMapper.insert(ext);
					//文件流拷贝
					String name = ext.getEpath()+ext.getEid()+ext.getEsuffix();
					OutputStream out = new FileOutputStream(name);
					out.write(file.getBytes());
					out.flush();
					out.close();
				}
			}
			result.put("code", 200);
			result.put("message", "帖子上传成功");
			
		}else{
			result.put("code", 407);
			result.put("message", "帖子上传失败");
		}
		return result;
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS)
	public Ext extDownload(String id) throws Exception {
		return extMapper.findById(id);
	}

	

}
