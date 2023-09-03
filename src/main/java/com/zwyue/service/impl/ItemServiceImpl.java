package com.zwyue.service.impl;

import com.github.pagehelper.PageHelper;
import com.zwyue.util.jedis.JedisClient;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;
import javax.jms.Destination;

//@Service
public class ItemServiceImpl{


	@Resource
	private Destination topicDestination;


	@Autowired
	private JedisClient jedisClient;


	@Value("${REDIS_ITEM_PRE}")
	private String REDIS_ITEM_PRE;
	
	@Value("${ITEM_CACHE_EXPIRE}")
	private Integer ITEM_CACHE_EXPIRE;

	public void getItemById(long itemId) {
		//查询缓存
				try {
					String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":BASE");
					if(StringUtils.isNotBlank(json)) {
//						String tbItem = JsonUtils.jsonToPojo(json, String.class);
						return ;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//缓存中没有，查询数据库
				//根据主键查询
				//TbItem tbItem = itemMapper.selectByPrimaryKey(itemId);
//				TbItemExample example = new TbItemExample();
//				Criteria criteria = example.createCriteria();
//				//设置查询条件
//				criteria.andIdEqualTo(itemId);
//				//执行查询
//				List<TbItem> list = itemMapper.selectByExample(example);
				if (1==1) {
					//把结果添加到缓存
					try {
						jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":BASE",null);
						//设置过期时间
						jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":BASE",ITEM_CACHE_EXPIRE);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return ;
				}
				return;
	}

	public void getItemList(int page, int rows) {
				//设置分页信息
				PageHelper.startPage(page, rows);
//				//执行查询
//				TbItemExample example = new TbItemExample();
//				/*Criteria criteria = example.createCriteria();
//				criteria.andStatusEqualTo((byte)1);*/
//				List<TbItem> list = itemMapper.selectByExample(example);
//				//取分页信息
//				PageInfo<TbItem> pageInfo = new PageInfo<>(list);
//
//				//创建返回结果对象
//				EasyUIDataGridResult result = new EasyUIDataGridResult();
//				result.setTotal(pageInfo.getTotal());
//				result.setRows(list);
//
//				return result;
	}

	public void addItem( String desc) {
//				// 1、生成商品id
//				final long itemId = IDUtils.genItemId();
//				// 2、补全TbItem对象的属性
//				item.setId(itemId);
//				//商品状态，1-正常，2-下架，3-删除
//				item.setStatus((byte) 1);
//				Date date = new Date();
//				item.setCreated(date);
//				item.setUpdated(date);
//				// 3、向商品表插入数据
//				itemMapper.insert(item);
//				// 4、创建一个TbItemDesc对象
//				TbItemDesc itemDesc = new TbItemDesc();
//				// 5、补全TbItemDesc的属性
//				itemDesc.setItemId(itemId);
//				itemDesc.setItemDesc(desc);
//				itemDesc.setCreated(date);
//				itemDesc.setUpdated(date);
//				// 6、向商品描述表插入数据
//				itemDescMapper.insert(itemDesc);
//				//发送商品添加消息
//				jmsTemplate.send(topicDestination, new MessageCreator() {
//
//					@Override
//					public Message createMessage(Session session) throws JMSException {
//						TextMessage textMessage = session.createTextMessage(itemId + "");
//						return textMessage;
//					}
//				});
//
//				// 7、E3Result.ok()
//				return E3Result.ok();

	}

	public void getItemDescList(long itemId) {
//		TbItemDescExample example = new TbItemDescExample();
//		cn.e3mall.pojo.TbItemDescExample.Criteria criteria = example.createCriteria();
//		//设置查询条件
//		criteria.andItemIdEqualTo(itemId);
//		//执行查询
//		List<TbItemDesc> list = itemDescMapper.selectByExampleWithBLOBs(example);
//		if(list!=null && list.size()>0){
//			return list.get(0);
//		}
//		return null;
	}

	public void updateItem(String desc) {
//		itemMapper.updateByPrimaryKeySelective(item);
//		TbItemDesc itemdesc = new TbItemDesc();
//		itemdesc.setItemId(item.getId());
//		itemdesc.setItemDesc(desc);
//		itemDescMapper.updateByPrimaryKeySelective(itemdesc);
//		return E3Result.ok();
	}

	public void deleteItem(long id) {
		/*TbItem item = new TbItem();
		item.setId(id);
		item.setStatus((byte)3);*/
//		int result = itemMapper.deleteByPrimaryKey(id);
//		if(result>0){
//			return E3Result.ok();
//		}else{
//			return null;
//		}
	}

	public void downItem(long id) {
//		TbItem item = new TbItem();
//		item.setId(id);
//		item.setStatus((byte)2);
//		int result = itemMapper.updateByPrimaryKeySelective(item);
//		if(result>0){
//			return E3Result.ok();
//		}else{
//			return null;
//		}
	}


	public void getItemDescById(long itemId) {
		//查询缓存
				try {
					String json = jedisClient.get(REDIS_ITEM_PRE + ":" + itemId + ":DESC");
					if(StringUtils.isNotBlank(json)) {
//						TbItemDesc tbItemDesc = JsonUtils.jsonToPojo(json, TbItemDesc.class);
						return ;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
//				TbItemDesc itemDesc = itemDescMapper.selectByPrimaryKey(itemId);
				//把结果添加到缓存
				try {
					jedisClient.set(REDIS_ITEM_PRE + ":" + itemId + ":DESC", null);
					//设置过期时间
					jedisClient.expire(REDIS_ITEM_PRE + ":" + itemId + ":DESC", ITEM_CACHE_EXPIRE);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return ;
	}

//
//	@Override
//	public Integer enterStudentInfo(Student student, StudentEnter studentEnter, Roster roster) {
//		return null;
//	}
//
//	@Override
//	public List<Student> queryAllStudents() {
//		return null;
//	}
}
