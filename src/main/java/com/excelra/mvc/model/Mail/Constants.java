package com.excelra.mvc.model.Mail;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.StringTokenizer;

public class Constants {
	
	public static String convertBeanToXMLString(Object bean)
	{
		StringBuffer sb = new StringBuffer();
		try {
			File tempFile = new File("request.xml");
			XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(tempFile)));
			// Write an XML representation of the specified object to the output.
			encoder.writeObject(bean);
			encoder.close();						
			FileInputStream fstream = new FileInputStream(tempFile);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
			  // Print the content on the console
			  sb.append(strLine);
			}
			//Close the input stream
			in.close();
			tempFile = null;
			fstream.close();
			fstream = null;
			encoder = null;
			in = null;
			br = null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			String className = new Throwable().getStackTrace()[0].getClassName();  
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			Constants.writeExceptionLog(className, methodName, e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			String className = new Throwable().getStackTrace()[0].getClassName();  
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			Constants.writeExceptionLog(className, methodName, e);
		}
		return sb.toString();
	  	    
	}
	
	public static String getToDate(Connection con)
	{
		Statement stmt = null;
		ResultSet rs = null;
		String qry = "";
		String today = "";
		
		try {
			stmt = con.createStatement();
			qry = "select to_char(now(),'MM/DD/YYYY hh24:mi:ss') as today";
			rs = stmt.executeQuery(qry);
			if(rs.next())
				today = rs.getString("today");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			String className = new Throwable().getStackTrace()[0].getClassName();  
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			Constants.writeExceptionLog(className, methodName, e);
		}
		
		/*Calendar today = new GregorianCalendar();
    	
    	int year1 = today.get(Calendar.YEAR);             // 2002
    	int month1 = today.get(Calendar.MONTH)+1;           // 0=Jan, 1=Feb, ...
    	int day1 = today.get(Calendar.DAY_OF_MONTH);
    	
    	return month1+"/"+day1+"/"+ year1;*/
		return today;
	}
	/*public static boolean insertQueryForCLOB(String query,Connection con,String tableName,String colName,String whereClause, String value)
	{
		Statement stmt = null;
		ResultSet rs = null;
		Statement selStat = null;
		boolean insert = false;
		try
		{
			stmt = con.createStatement();
			
			int i = stmt.executeUpdate(query);
			if(i>0)
			{
				
				//select that same record from the table and get a pointer to the CLOB object
				selStat = con.createStatement();
		
				//note that FOR UPDATE is necessary at the end of the statement to mark pointer to CLOB that it is 
				//retrieved for update
				query = "select "+colName+" FROM "+tableName+" "+whereClause+" FOR UPDATE";
				
				selStat.executeQuery(query);
				rs = selStat.getResultSet();
				
				if (rs.next())
				{	
					//oracle.sql.CLOB is the class provided by Oracle to be used to store pointer to the CLOB object
					oracle.sql.CLOB clob = (oracle.sql.CLOB)rs.getClob(1);
					
				//get output stream from the CLOB object
					OutputStream os = clob.getAsciiOutputStream();
					OutputStreamWriter osw = new OutputStreamWriter(os);
					
				//use that output stream to write character data to the Oracle data store
					osw.write(value.toCharArray());
					//write data and commit
					osw.flush();
	//						userCon.commit();
					osw.close();
					os.close();
					insert = true;
				}
				else
					insert = false;
			}
			else
				insert = false;
		}
		catch(Exception ex)
		{
			//ex.printStackTrace();
			insert = false;
		}
		finally
		{
			try {
				if(rs!=null)
				{
					rs.close();
					rs = null;
				}
				if(selStat!=null)
				{
					selStat.close();
					selStat = null;
				}
				if(stmt!=null)
				{
					stmt.close();
					stmt = null;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		return insert;
	}*/
	public static double round(double value, int decimalPlace)
	{
	  double power_of_ten = 1;
	  while (decimalPlace-- > 0)
	     power_of_ten *= 10.0;
	  return Math.round(value * power_of_ten) / power_of_ten;
	}
	public static String checkZeros(double value)
	{
		String tempValue = ""+value;
		String decValue = tempValue.substring(tempValue.indexOf(".")+1,tempValue.length());
		String actValue = tempValue.substring(0,tempValue.indexOf("."));
		if("0".equals(decValue) || "0".equals(actValue))
			return actValue;
		else 
			return tempValue; 
			
	}
	
	public static float checkNullForDouble(String colValue)
	{
		if(colValue==null)
			return 0;
		else
			return Float.valueOf(colValue);
	}
	public static int getNextNumber(String tableName,Connection con,String colName)
	{
		String sql = "";
		Statement stmt = null;
		ResultSet rs = null;
		int k=0;
		try
		{
			stmt = con.createStatement();
			sql = "select max("+colName+") from "+tableName;
			rs = stmt.executeQuery(sql);
			if(rs.next())
				k = rs.getInt(1);
		}
		catch(SQLException sqlex)
		{
			
			String className = new Throwable().getStackTrace()[0].getClassName();  
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			Constants.writeExceptionLog(className, methodName, sqlex);
			return 0;
		}
		return k+1;
	}
	public static boolean containsOnlyNumbersWithSpecChar(String str,char ch) {
        List<String> tempList = new ArrayList<String>();
        
        if(str.contains(" "))
        	str= str.replaceAll("\\s","");
        if(str.endsWith(","))
        	str= str.substring(0,str.lastIndexOf(","));
        
        //It can't contain only numbers if it's null or empty...
        if (str == null || str.length() == 0)
            return false;
        tempList = Arrays.asList(str.split(Constants.comma_str));
        boolean flag =false;
        for(int i=0;i<tempList.size();i++)
        {
        	try
        	{
        		int tmp = Integer.parseInt(tempList.get(i));
        		flag = true;
        	}
        	catch(NumberFormatException nfe)
        	{
        		String className = new Throwable().getStackTrace()[0].getClassName();  
    			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
    			Constants.writeExceptionLog(className, methodName, nfe);
        		flag =false;
        		break;
        	}
        	
        }
        return flag;
        /*boolean flag =false;
        String []tempStr = str.split(",");
        for (int i = 0; i < tempStr.length; i++) {

            //If we find a non-digit character we return false.
            if (!Character.isDigit(str.charAt(i)) && (str.charAt(i) != Constants.comma))
            {
            	
                //return false;
            	flag =false;
            	break;
            }
            else
            {
            	flag =true;
            }
        }
        if(flag)
        	return true;
        else
        	return false;*/
    }

	public static float Round(float Rval, int Rpl) {
		  float p = (float)Math.pow(10,Rpl);
		  Rval = Rval * p;
		  float tmp = Math.round(Rval);
		  return tmp/p;
		    }
	
	public static String convertListToStringWithDelimiter(List<String> coll, String delimiter)
	{
	    if (coll.isEmpty())
		return "";
	 
	    StringBuilder sb = new StringBuilder();
	 
	    for (String x : coll)
		sb.append(x + delimiter);
	 
	    sb.delete(sb.length()-delimiter.length(), sb.length());
	    
	    String result = sb.toString();
	    if(result.endsWith(","))
	    	result =result.substring(0,result.lastIndexOf(","));
	    return result;
	}
	
	public static String[] removeDuplicates(String[]array)
	{
		Set<String> arraySet = new HashSet<String>(Arrays.asList(array));
		return arraySet.toArray(new String[]{});
	}
	
	public static String removeDuplicates(String array,String delim)
	{
		Set<String> arraySet = new HashSet<String>(Arrays.asList(array.split(delim)));
		
		return Constants.arrayToString(arraySet.toArray(new String[]{}),delim);
	}
	
	public static String[] concatTwoStringArrays(String[] first, String[] second) 
	{    
		List<String> both = new ArrayList<String>();    
		Collections.addAll(both, first);    
		Collections.addAll(both, second);    
		return both.toArray(new String[] {});
	}
	
	public static String arrayToString(String[] a, String separator) {
	    String result = "";
	    if(a!=null)
	    {
	    	
	        for (int i=0; i<a.length; i++) 
	        {
	        	if(a[i]!=",")
	        		result = result + separator + a[i];	            
	        }
		    if(result.endsWith(","))
		    	result =result.substring(0,result.lastIndexOf(","));
		    if(result.startsWith(","))
		    	result =result.substring(1,result.length());
	    }
	    return result;
	}
	
		
	public static String setDateFormat(String frmDate)
	{
		String month ="";
		String day ="";
		String year ="";
		if(frmDate!=null && !"".equals(frmDate))
		{
			month = frmDate.substring(frmDate.indexOf("/")+1,frmDate.lastIndexOf("/"));
			day = frmDate.substring(0,frmDate.indexOf("/"));
			year = frmDate.substring(frmDate.lastIndexOf("/")+1);
		}
		
		
		if("01".equals(month))
			month = "JAN";
		else if("02".equals(month))
			month = "FEB";							
		else if("03".equals(month))
			month = "MAR";
		else if("04".equals(month))
			month = "APR";
		else if("05".equals(month))
			month = "MAY";
		else if("06".equals(month))
			month = "JUN";
		else if("07".equals(month))
			month = "JUL";
		else if("08".equals(month))
			month = "AUG";
		else if("09".equals(month))
			month = "SEP";
		else if("10".equals(month))
			month = "OCT";
		else if("11".equals(month))
			month = "NOV";
		else if("12".equals(month))
			month = "DEC";
		
		String modDate = month+" "+day+", "+year;
		
		return modDate;
	}
	public static Map<String,String> getTokenValue(String decode)
	{
		StringTokenizer stk = new StringTokenizer(decode,"&");
		HashMap<String,String>qryMap = new HashMap<String, String>(stk.countTokens());
		while(stk.hasMoreTokens())
		{
			String token = stk.nextToken();
			
			//String[]str = token.split("=");
			String str1 = token.substring(0,token.indexOf("="));
			String str2 = token.substring(token.indexOf("=")+1);
			
			try
			{
				qryMap.put(Constants.checkNull(str1), Constants.checkNull(str2));
			}
			catch(ArrayIndexOutOfBoundsException ai)
			{
				String className = new Throwable().getStackTrace()[0].getClassName();  
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				Constants.writeExceptionLog(className, methodName, ai);
				qryMap.put(Constants.checkNull(str1), "");
			}
		}
		return qryMap;
	}
	
	public static String checkNullAndInComa(Object obj)
	{
		String s="";
		if(obj!=null)
		{
			s=obj.toString().trim();
			if(s.indexOf("'")>0)
				return s.replace("'", "\''");
			return s;
		}
		else
			return s;
	}
	public static String checkNull(Object obj)
	{
		if(obj!=null && !"null".equals(obj))
			return obj.toString().trim();
		else
			return "";
	}
	public static String checkNull_space(Object objVal)
	{
		if(objVal==null || objVal.toString().equalsIgnoreCase("null"))
			return "";
		else
			return objVal.toString().trim();
	}

	public static String checkNull_put_dot(Object objVal)
	{
		if(objVal==null || objVal.toString().equalsIgnoreCase("null"))
			return "..";
		else
			return objVal.toString().trim();
	}
	
	public static String getWhereForInClause4IDs(List<String> extraList)
	{
		double noOfIterations = Math.ceil(extraList.size()/1000.0);
		
		String where = "";
		int j=0;
		int l = 0;
		
		for(int k=0;k<noOfIterations;k++)
		{
						try
			{
				l=j;
				where += " gvk_id in ("+Constants.convertListToStringWithDelimiter(extraList.subList(j,j+=1000),",")+") or ";
			}
			catch(Exception ex)
			{
				where += " gvk_id in ("+Constants.convertListToStringWithDelimiter(extraList.subList(l,extraList.size()),",")+") or ";
			}
		}
		if(where.endsWith("or "))
		{
			where = where.substring(0,where.lastIndexOf("or"));
		}
		return where;
	}
	
	public static String getWhereForInClause(List<String> extraList)
	{
		double noOfIterations = Math.ceil(extraList.size()/1000.0);
	
		
		String where = "";
		int j=0;
		int l = 0;
		
		for(int k=0;k<noOfIterations;k++)
		{
			

			try
			{
				l=j;
				where += " gvk_id in ("+Constants.convertListToStringWithDelimiter(extraList.subList(j,j+=1000),",")+") or ";
			}
			catch(Exception ex)
			{
				where += " gvk_id in ("+Constants.convertListToStringWithDelimiter(extraList.subList(l,extraList.size()),",")+") or ";
			}
		}
		if(where.endsWith("or "))
		{
			where = where.substring(0,where.lastIndexOf("or"));
		}
		return where;
	}
	public static String getWhereForInClauseNew(List<String> extraList,String column_name)
	{
		double noOfIterations = Math.ceil(extraList.size()/1000.0);
		String where = "";
		int j=0;
		int l = 0;
		
		for(int k=0;k<noOfIterations;k++)
		{
			try
			{
				l=j;
				where += column_name+" in ("+Constants.convertListToStringWithDelimiter(extraList.subList(j,j+=1000),",")+") or \n";
			}
			catch(Exception ex)
			{
				where += column_name+" in ("+Constants.convertListToStringWithDelimiter(extraList.subList(l,extraList.size()),",")+") or \n";
			}
		}
		if(where.endsWith("or \n"))
		{
			where = where.substring(0,where.lastIndexOf("or"));
		}
		return where;
	}
	public static String getWhereForInClauseForCasNo(List<String> extraList,String column_name)
	{
		double noOfIterations = Math.ceil(extraList.size()/1000.0);
		String where = "";
		int j=0;
		int l = 0;
		
		for(int k=0;k<noOfIterations;k++)
		{
			try
			{
				l=j;
				where += " trim("+column_name+") in ("+Constants.convertListToStringWithDelimiter(extraList.subList(j,j+=1000),",")+") or \n";
			}
			catch(Exception ex)
			{
				where += " trim("+column_name+") in ("+Constants.convertListToStringWithDelimiter(extraList.subList(l,extraList.size()),",")+") or \n";
			}
		}
		if(where.endsWith("or \n"))
		{
			where = where.substring(0,where.lastIndexOf("or"));
		}
		return where;
	}
	public static String getWhereForInClause_STRIDs(List<String> extraList)
	{
		double noOfIterations = Math.ceil(extraList.size()/1000.0);
		
		String where = "";
		int j=0;
		int l = 0;
		
		for(int k=0;k<noOfIterations;k++)
		{
			

			try
			{
				l=j;
				where += " str_id in ("+Constants.convertListToStringWithDelimiter(extraList.subList(j,j+=1000),",")+") or ";
			}
			catch(Exception ex)
			{
				where += " str_id in ("+Constants.convertListToStringWithDelimiter(extraList.subList(l,extraList.size()),",")+") or ";
			}
		}
		if(where.endsWith("or "))
		{
			where = where.substring(0,where.lastIndexOf("or"));
		}
		return where;
	}
	
	public static String checkNull_put_zero(Object objVal)
	{
		if(objVal==null || objVal.toString().equalsIgnoreCase("null"))
			return "0";
		else
			return objVal.toString().trim();
	}
	
	public static void writeExceptionLog(String className, String methodName, Exception e)
	{
		java.text.SimpleDateFormat sdformat =new java.text.SimpleDateFormat("MM-dd-yy");
		
		String osName = System.getProperty("os.name");
		
		ResourceBundle resource = ResourceBundle.getBundle("com.gvk.resources.gostar");
		String filepath= resource.getString("logfile.path");
		//String fileName =filepath+"/gostar_"+sdformat.format(new java.util.Date())+".log";
		String fileName ="gostar_"+sdformat.format(new java.util.Date())+".txt";
		//=====
		try
		{
			File f = null;
				if(osName.contains("Windows"))						
		        	//f = new File("c:\\GOSTAR_Logs\\"+fileName);
					f = new File(filepath+fileName);
		        else
		        	f = new File(filepath+fileName);
				
				java.io.FileWriter fstream = new java.io.FileWriter(f, true);
				java.io.BufferedWriter out = new java.io.BufferedWriter(fstream);
				if((e.getMessage()!=null) && (!(e.getMessage().equals("null"))))
					out.write("Class Name:"+className +"\t Method Name:"+methodName+"\t Date:"+new java.util.Date()+" \t Exception:"+e.getMessage()+"\n");
				out.close();
		}
		catch(Exception ex){
			//ex.printStackTrace();
		}
		
		
	}
	
	public static String per = "%";
	public static String DATABASE_FLAG = "NEW";
	public static String DATABASE_REPLICATED = "NO";
	public static String DATABASE_FLAG_DELETE ="DEL";
	
	public static char comma = ',';
	public static String comma_str = ",";
	public static int eight = 8;
	public static int percost = 10;
	public static String ORDER_TYPE_STR = "orderType";
	public static String FORWARD_SUCCESS="success";
	public static String FORWARD_FAILURE="failure";
	public static String PARAM_COMPOUNDTYPE="compoundType";
	public static String PARAM_ALL="All";
	public static String MYSESSION="mysession";
	public static String STRING_EMPTY="";
	public static String RESOURCEBUNDLE_NAME="gostar";
	public static String STR_ID = "STR_ID";
	public static String SUB_SMILES = "SUB_SMILES";
	public static String No_Of_SimStrs =  "no_of_simstrs";
	public static String SMILES = "SMILES";
	public static String DATABASE_NAME = "database_name";
	public static String DATABASE_STR = "database";
	public static String FIELDVALUE_STR = "<FIELD_VALUE>";
	public static String Count_GVKIDs = "count(gvkid)";
	public static String ACTIVITY_TYPE = "activityType";
	public static String ONE = "1";
	public static String TWO = "2";
	public static String THREE = "3";
	public static String STRUCTURE = "Structure";
	public static String VIEW_ONE = "ViewOne";
	public static String VIEW_TWO = "ViewTwo";
	public static String LOGIN = "login";
	public static String FAILURE = "failure";
	public static String SUCCESS = "success";
	public static String NO_HITS_FOUND = "noHits";
	
	public static String QUERY_4_ACTIVITIES = " select a.gvk_id,b.str_id from <TABLE_NAME> a, tttt b where a.gvk_id=b.gvk_id group by str_id,a.gvk_id order by str_id ";
	
	
	public static String Query_4_VIEWONE_DB = "select distinct str_id,sub_smiles,count(*) from tttt where database_name='<FIELD_VALUE>' group by str_id,sub_smiles";
	public static String QUERY_4_VIEW_ONE = "select distinct str_id,sub_smiles,count(*) from tttt group by str_id,sub_smiles";
	public static String Query_4_ViewOneCntDB = "select distinct database_name , count(*) from tttt where database_name='<FIELD_VALUE>'";
	
	public static String QUERY_4_VIEW_TWO = "select SUB_SMILES,GVK_ID from tttt";
	public static String Query_4_VIEWTWO_DB = "select SUB_SMILES,GVK_ID from tttt where database_name='<FIELD_VALUE>'";
	
	
	public static String BEAN_RECS_STR = "beanRecords";//session name for list of all records
	public static String BEAN_TABLE_STR = "beanForTable";//session name for table string
	public static String BEAN_CNTDBS_STR = "beanForCntDBs";//session name for db counts
	public static String BEAN_PAGIN_STR = "beanForPagination";//session name for pagination
	public static String FORWARD_NAME_STR = "forwardName";
	public static String QUERY_4_CNT_DBS = "select database_name, count(gvk_id) from tttt  group by database_name";
	public static String MAX_REC_PER_PAGE = "maxRecPerPage";
	public static String CUR_PAGE_STR = "curPage";//this name exists in viewStructure or viewAcivities pages navigation functions, for getting the value from the request by using this name
	public static String TYPE_OF_ACTIVITY_STR = "typeOfActivity";
	public static String INVALID_LOGIN_STR = "INVALID_LOGIN";
	public static String USER_LOGIN_STATUS_STR = "user_login_status";
	public static String BEAN_PARAMS_STR = "beanForParameters";
	public static String BEAN_HIGH_PHASE_STR = "highPhaseList";
	public static String BEAN_SPECIES_STR = "species";
	public static String ALL_ACTIVITY_STR = "all_activity";
	public static String BUNDLE_NAME = "gostar";
	public static String DRIVER_NAME_STR = "driver.title";
	public static String DRIVER_URL_STR = "url.title";
	public static String DRIVER_USERNAME_STR = "dbuser.title";
	public static String DRIVER_PASSWORD_STR = "dbpassword.title";
	public static int NEEDED_RECORDS_TWO = 10;
	public static int NEEDED_RECORDS_ONE = 5;
	public static String ACTIVITY_VALUE_STR = "activityValue";
	public static String FORWARD_ACTIVITIES = "activities";
	public static String FORMAT_VIEW_STR = "formatView";
	public static String INVITRO_STR = "Invitro";
}
