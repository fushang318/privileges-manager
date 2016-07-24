package com.lesaas.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;

import com.lesaas.model.Page;


/**
 * @see(功能介绍) : 基本DAO
 * @version(版本号): 1.0
 */

@Component
public class BaseDao<T> extends SimpleHibernateDao<T, String> {
	
	
	public List<?> getByHql(String hql)
	{
		Query query=this.createQuery(hql);
		return query.list();
	}
	
	/**
	 * 本地sql查询，可以带参,无分页
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> querySql(String sql, Object... values)
			throws Exception {
		Query query = this.createSQLQuery(sql, values);
		return query.list();
	}
	
	/**
	 * 为报表准备的
	 * @param hql
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getDataByHql(String hql)
	{
		Query query=this.createQuery(hql);
		return query.list();
	}
	
	
	 /**
	  * HQL分页查询，可以带参
	  */
	public void  queryPageHql(String hql,Page page,Object... values) throws Exception {
		Query query = this.createQuery(hql, values);
		query.setFirstResult((page.getPageNo()-1)*page.getNumPerPage());
		query.setMaxResults(page.getNumPerPage());
		page.setResult(query.list());
		if(page.getPageNo()==1){
			String countHql="select count(*) from "+StringUtils.substringAfter(hql, "from");
			countHql=StringUtils.substringBefore(countHql,"order");
			query = this.createQuery(countHql, values);
			page.setTotalCount(new Integer(query.uniqueResult().toString()));
		}
	}
	
	 /**
	  * HQL分页查询，可以带参
	  */
	public void  queryPageHqlSubGroup(String hql,Page page,Object... values) throws Exception {
		Query query = this.createQuery(hql, values);
		query.setFirstResult((page.getPageNo()-1)*page.getNumPerPage());
		query.setMaxResults(page.getNumPerPage());
		page.setResult(query.list());
		if(page.getPageNo()==1){
			String countHql="select count(*) from "+StringUtils.substringAfter(hql, "from");
			countHql=StringUtils.substringBefore(countHql,"group");
			query = this.createQuery(countHql, values);
			page.setTotalCount(new Integer(query.uniqueResult().toString()));
		}
	}
	
	/**
	 * 本地sql分页查询，可以带参
	 */
	public void  queryPageSql(String sql,Page page,Object... values) throws Exception {
		Query query = this.createSQLQuery(sql, values);
		query.setFirstResult((page.getPageNo()-1)*page.getNumPerPage());
		query.setMaxResults(page.getNumPerPage());
		page.setResult(query.list());
		if(page.getPageNo()==1){
			String countHql="select count(*) from "+StringUtils.substringAfter(sql, "from");
			countHql=StringUtils.substringBefore(countHql,"order");
			query = this.createSQLQuery(countHql, values);
			page.setTotalCount(new Integer(query.uniqueResult().toString()));
		}
	}
	
	/**
	 * 本地sql分页查询，可以带参
	 */
	public void  queryProcessPageSql(String sql,Page page,Object... values) throws Exception {
		Query query = this.createSQLQuery(sql, values);
		query.setFirstResult((page.getPageNo()-1)*page.getNumPerPage());
		query.setMaxResults(page.getNumPerPage());
		page.setResult(query.list());
		if(page.getPageNo()==1){
//			String countHql="select count(*) from "+StringUtils.substringAfter(sql, "from");
			StringBuffer countHql = new StringBuffer("select count(1) as c from ( ");
			countHql.append(sql);
			countHql.append(") c");
//			countHql=StringUtils.substringBefore(countHql,"order");
			query = this.createSQLQuery(countHql.toString(), values);
			page.setTotalCount(new Integer(query.uniqueResult().toString()));
		}
	}
	/**
	 * 本地sql分页查询，可以带参
	 */
	public List<?>  queryPageSqlIsNoPage(String sql,Page page,Object... values) throws Exception {
		Query query = this.createSQLQuery(sql, values);
		if(page.getIsPaged()){
		query.setFirstResult((page.getPageNo()-1)*page.getNumPerPage());
		query.setMaxResults(page.getNumPerPage());
		}
		page.setResult(query.list());
		if(page.getPageNo()==1){
			String countHql="select count(*) from "+StringUtils.substringAfter(sql, "from");
			query = this.createSQLQuery(countHql, values);
			page.setTotalCount(new Integer(query.uniqueResult().toString()));
		}
		return page.getResult();
	}
	
	/**
	 * 本地sql分页查询，可以带参，带子查询
	 */
	public void  queryPageSqlSub(String sql,Page page,Object... values) throws Exception {
		Query query = this.createSQLQuery(sql, values);
		query.setFirstResult((page.getPageNo()-1)*page.getNumPerPage());
		query.setMaxResults(page.getNumPerPage());
		page.setResult(query.list());
		if(page.getPageNo()==1){
			String countHql="select count(*) from "+StringUtils.substringAfterLast(sql, "from");
			countHql=StringUtils.substringBefore(countHql,"order");
			query = this.createSQLQuery(countHql, values);
			page.setTotalCount(new Integer(query.uniqueResult().toString()));
		}
	}
	
	/**
	 * 本地sql分页查询，可以带参，带子查询
	 */
	public void  queryPageSqlSubF(String sql,String countSql,Page page,Object... values) throws Exception {
		Query query = this.createSQLQuery(sql, values);
		query.setFirstResult((page.getPageNo()-1)*page.getNumPerPage());
		query.setMaxResults(page.getNumPerPage());
		page.setResult(query.list());
		if(page.getPageNo()==1){
			String countHql=countSql;
			countHql=StringUtils.substringBefore(countHql,"order");
			query = this.createSQLQuery(countHql, values);
			page.setTotalCount(new Integer(query.uniqueResult().toString()));
		}
	}
	
	/**
	 * 命名查询，参数为序列号，如0,1,2
	 * @param name 
	 * @param page
	 * @param objects 参数值
	 * @return
	 * @throws Exception
	 */
	public void  queryNamedSql(String queryName,Page page,Object... values) throws Exception {
		Query query = createQueryByName(queryName, values);
		query.setFirstResult((page.getPageNo()-1)*page.getNumPerPage());
		query.setMaxResults(page.getNumPerPage());
		page.setResult(query.list());
		if(page.getPageNo()==1){
			String countSql="select count(*) from "+StringUtils.substringAfter(query.getQueryString(), "from");
			query = this.createSQLQuery(countSql, values);
			page.setTotalCount(new Integer(query.uniqueResult().toString()));
		}
	}
	
	/**
	 * 命名参数查询
	 * @param name  命名参数
	 * @param page 
	 * @param values  参数对应值列表
	 * @return
	 * @throws Exception
	 */
	public void queryNamedSql(String queryName,Page page, Map<String, Object> values) throws Exception {
		Query query = createQueryByName(queryName, values);
		query.setFirstResult((page.getPageNo()-1)*page.getNumPerPage());
		query.setMaxResults(page.getNumPerPage());
		page.setResult(query.list());
		if(page.getPageNo()==1){
		  String countSql="select count(*) from "+StringUtils.substringAfter(query.getQueryString(), "from");
		  query = this.createSQLQuery(countSql, values);
		  page.setTotalCount(new Integer(query.uniqueResult().toString()));
		}
	}
	/**
	 * 存储过程调用,位置参数
	 * @param procName
	 * @param page
	 * @param values
	 * @throws Exception
	 */
	 public void queryProcedure(String procName,Page page,Object...values) throws Exception
	 {
		 Query query = createSQLQuery("{Call "+procName+"}",values);
		 int i=0;
		 for(Object o:values)
		 query.setParameter(i++, o);
		 query.setFirstResult((page.getPageNo()-1)*page.getNumPerPage());
		 query.setMaxResults(page.getNumPerPage());
		 page.setResult(query.list());
	 }
	 /**
	 * 本地sql分页查询，可以带参正对group by的语句
	 */
	public List<?>  queryPageSqlGroup(String sql,Page page,Object... values) throws Exception {
		Query query = this.createSQLQuery(sql, values);
		if(page.getIsPaged()){
		query.setFirstResult((page.getPageNo()-1)*page.getNumPerPage());
		query.setMaxResults(page.getNumPerPage());
		}
		page.setResult(query.list());
		if(page.getPageNo()==1){
			//String countHql="select count(*) from "+StringUtils.substringAfter(sql, "from");
			String countHql="select count(*) from ("+sql+")";
			query = this.createSQLQuery(countHql, values);
			page.setTotalCount(new Integer(query.uniqueResult().toString()));
		}
		return page.getResult();
	}
	
	/**
	 * 根据类型查询list
	 */
	public List<?> getTAll(Class<?> clazz,String... order){
		Criteria criteria=getSession().createCriteria(clazz);
		for (String str : order) {
			Order o=Order.asc(str);
			criteria=criteria.addOrder(o);
		}
		return criteria.list();
	}
	
	/**
	 * 根据类型查询list
	 */
	public List<?> getTAll(String sql,Class<?> clazz){
		SQLQuery query = this.createSQLQuery(sql);
		return query.addEntity(clazz).list();
	}	
	
	/**
	 * *Mysql/Oracle自适应
     */
	
	public String mySqlOraConvert(String convType,String funcType,String... paras) throws Exception{
		String dbType=this.createSQLQuery("select DBType from Bas_SysPara").uniqueResult().toString();
		if (paras ==null || paras.length==0) return "";
		if ("1".equals(convType)){//分页处理
				if ("1".equals(dbType)){//mysql
					return paras[0]+" limit "+paras[1]
								+(paras.length>2?(","+paras[2]):"");
				}else{//oracle
					return " select t.*,rownum rn from ("+paras[0]+") t " +
						   "  where t.rn>"+paras[1]+" " +
						   		"and t.rn<="+paras[2];
				}
		}else{//特殊函数处理
			if ("nullCast".equals(funcType)){//空值处理
				if ("1".equals(dbType)){//mysql
					return " ifnull("+paras[0]+","+paras[1]+")";
				}else{//oracle
					return " nvl("+paras[0]+","+paras[1]+")";
				}
			}else if ("dateToStr".equals(funcType)){//日期转字符串
				if ("1".equals(dbType)){//mysql
					return "date_format('" + paras[0] + "','%Y-%m-%d')";
				}else{//oracle
					return "to_char('"+paras[0]+"','yyyy-mm-dd')";
				}
			}else if ("timeToStr".equals(funcType)){//时间转字符串
				if ("1".equals(dbType)){//mysql
					return "date_format('" + paras[0] + "','%Y-%m-%d %H:%i:%s')";
				}else{//oracle
					return "to_char('"+paras[0]+"','yyyy-mm-dd h24:mi:ss')";
				}
			}else if ("strToDate".equals(funcType)){//字符串转日期
				if ("1".equals(dbType)){//mysql
					return "str_to_date('" + paras[0] + "','%Y-%m-%d')";
				}else{//oracle
					return "to_date('"+paras[0]+"','yyyy-mm-dd')";
				}
			}else if ("strToTime".equals(funcType)){//字符串转时间
				if ("1".equals(dbType)){//mysql
					return "str_to_date('" + paras[0] + "','%Y-%m-%d %H:%i:%s')";
				}else{//oracle
					return "to_date('"+paras[0]+"','yyyy-mm-dd h24:mi:ss')";
				}
			}else if ("strToNum".equals(funcType)){//字符串转数字
				if ("1".equals(dbType)){//mysql
					if (paras.length==1){
						return "cast('" + paras[0] + "' as signed int)";
					}else{
						return "cast('"+ paras[0] + "' as decimal("+paras[1]+","+paras[2]+"))";
					}
				}else{//oracle
					return "to_number('"+paras[0]+"')";
				}
			}else if("dateSubtract".equals(funcType)){//日期相减得到天数
				if("1".equals(dbType)){//mysql
					return "datediff("+paras[0]+","+paras[1]+")";
				}else{//oracle
					return "(To_date('"+paras[0]+"', 'yyyy-mm-dd') - "
							+ "To_date('"+paras[1]+"', 'yyyy-mm-dd')";
				}
			}else if("YMToStr".equals(funcType)){//日期-年月转字符串
				if ("1".equals(dbType)){//mysql{
					return "date_format('" + paras[0] + "','%Y%m')";
				}else{//Oracle
					return "to_date('"+paras[0]+"','yyyymm')";
				}
			}else if("YToStr".equals(funcType)){//日期-年转字符串
				if ("1".equals(dbType)){//mysql{
					return "date_format('" + paras[0] + "','%Y')";
				}else{//Oracle
					return "to_date('"+paras[0]+"','yyyy')";
				}
			}else if("MToStr".equals(funcType)){//日期-月转字符串
				if ("1".equals(dbType)){//mysql{
					return "date_format('" + paras[0] + "','%m')";
				}else{//Oracle
					return "to_date('" + paras[0] + "','mm')";
				}
			}
		}
		return "";
	}
}
