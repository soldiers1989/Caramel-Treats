package com.otc.api.db;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class GenEntityUtil {

	private String[] colnames; // 列名数组
	private String[] colTypes; // 列名类型数组
	private int[] colSizes; // 列名大小数组
	private boolean f_util = false; // 是否需要导入包java.util.*
	private boolean f_sql = false; // 是否需要导入包java.sql.*
	private StringBuffer result = new StringBuffer("\t@Results({ ");
	private StringBuffer insertResult = new StringBuffer("");
	private StringBuffer insertValue = new StringBuffer("");
	private StringBuffer updateResult = new StringBuffer("");

	public static void main(String[] args) throws IOException {
		String packagePath = "com.otc.api";
		String tableName = "yio_self";
		GenEntityUtil g = new GenEntityUtil(packagePath, tableName);
//		g.wirtFileMapper(packagePath, tableName);
		g.wirtFileService(packagePath, tableName);
		g.wirtFileController(packagePath, tableName);
	}

	public GenEntityUtil(String packagePath, String tableName) {
		packagePath = packagePath + ".domain";
		Connection conn = DBHelper.getConn(); // 得到数据库连接
		PreparedStatement pstmt = null;
		Statement stmt =null;
		ResultSet rs =null;
		String strsql = "select * from " + tableName;
		try {
			pstmt = conn.prepareStatement(strsql);
			ResultSetMetaData rsmd = pstmt.getMetaData();
			int size = rsmd.getColumnCount(); // 共有多少列
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			stmt = conn.createStatement();
			rs = stmt.executeQuery("show full columns from " + tableName);
			for (int i = 0; i < rsmd.getColumnCount(); i++) {
				colnames[i] = this.getCamelStr(rsmd.getColumnName(i + 1));
				result.append("@Result(property = \"" + colnames[i] + "\", column = \"" + rsmd.getColumnName(i + 1) + "\"),");
				insertResult.append(rsmd.getColumnName(i + 1) + ",");
				insertValue.append("#{" + colnames[i] + "},");
				updateResult.append(rsmd.getColumnName(i + 1)+"=#{" + colnames[i] + "},");
				
				colTypes[i] = rsmd.getColumnTypeName(i + 1);
				if (colTypes[i].equalsIgnoreCase("datetime")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("timestamp")) {
					f_util = true;
				}
				if (colTypes[i].equalsIgnoreCase("image") || colTypes[i].equalsIgnoreCase("text")) {
					f_sql = true;
				}
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}
			try {
				String content = parse(colnames, colTypes, colSizes, packagePath, tableName,rs);
				String path = System.getProperty("user.dir") + "/src/main/java/" + packagePath.replaceAll("\\.", "/");
				File file = new File(path);
				if (!file.exists()) {
					file.mkdirs();
				}
				String resPath = path + "/" + initcap(tableName) + ".java";
				FileUtils.writeStringToFile(new File(resPath), content);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBHelper.close2(conn, pstmt,rs,stmt);
		}
	}

	/**
	 * 解析处理(生成实体类主体代码)
	 * @throws SQLException 
	 */
	private String parse(String[] colNames, String[] colTypes, int[] colSizes, String packagePath, String tableName,ResultSet rs) throws SQLException {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + packagePath + ";\r\n\r\n");
		if (f_util) {
			sb.append("import java.util.Date;\r\n");
		}
		if (f_sql) {
			sb.append("import java.sql.*;\r\n\r\n\r\n");
		}
		sb.append("public class " + initcap(tableName) + " {\r\n\r\n");
		processAllAttrs(sb,rs);
		sb.append("\r\n");
		processAllMethod(sb);
		sb.append("}\r\n");
		return sb.toString();

	}

	/**
	 * 生成所有的方法
	 * 
	 * @param sb
	 */
	private void processAllMethod(StringBuffer sb) {
		for (int i = 0; i < colnames.length; i++) {
			sb.append("\tpublic void set" + initcap(colnames[i]) + "(" + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + "){\r\n");
			sb.append("\t\tthis." + colnames[i] + "=" + colnames[i] + ";\r\n");
			sb.append("\t}\r\n\r\n");

			sb.append("\tpublic " + sqlType2JavaType(colTypes[i]) + " get" + initcap(colnames[i]) + "(){\r\n");
			sb.append("\t\treturn " + colnames[i] + ";\r\n");
			sb.append("\t}\r\n\r\n");
		}
	}

	/**
	 * 解析输出属性
	 * 
	 * @return
	 * @throws SQLException 
	 */
	private void processAllAttrs(StringBuffer sb ,ResultSet rs) throws SQLException {
		while (rs.next()) {
		for (int i = 0; i < colnames.length; i++) {
				if(getCamelStr(rs.getString("Field")).equals(colnames[i])){
					sb.append("\t//"+rs.getString("Comment")+"\r\n");
					sb.append("\tprivate " + sqlType2JavaType(colTypes[i]) + " " + colnames[i] + ";\r\n");
				}
			}
		}
	}

	/**
	 * 把输入字符串的首字母改成大写
	 * 
	 * @param str
	 * @return
	 */
	private String initcap(String str) {
		char[] ch = str.toCharArray();
		if (ch[0] >= 'a' && ch[0] <= 'z') {
			ch[0] = (char) (ch[0] - 32);
		}
		return this.getCamelStr(new String(ch));
	}

	// 例：user_name --> userName
	private String getCamelStr(String s) {
		String column = s;
		while (s.indexOf("_") > 0) {
			int index = s.indexOf("_");
			// System.out.println(s.substring(index+1, index+2).toUpperCase());
			s = s.substring(0, index) + s.substring(index + 1, index + 2).toUpperCase() + s.substring(index + 2);
		}
		return s;
	}

	// 例：user_name --> userName
	private String getValuesCamelStr(String s) {
		String column = s;
		while (s.indexOf("_") > 0) {
			int index = s.indexOf("_");
			// System.out.println(s.substring(index+1, index+2).toUpperCase());
			s = s.substring(0, index) + s.substring(index + 1, index + 2).toUpperCase() + s.substring(index + 2);
		}
		return s;
	}

	private String sqlType2JavaType(String sqlType) {
		if (sqlType.equalsIgnoreCase("bit")) {
			return "boolean";
		} else if (sqlType.equalsIgnoreCase("tinyint")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("smallint")) {
			return "short";
		} else if (sqlType.equalsIgnoreCase("int") || sqlType.equalsIgnoreCase("integer") ||
				sqlType.equalsIgnoreCase("int unsigned")) {
			return "Integer";
		} else if (sqlType.equalsIgnoreCase("bigint")) {
			return "long";
		} else if (sqlType.equalsIgnoreCase("float")) {
			return "float";
		} else if (sqlType.equalsIgnoreCase("decimal") || sqlType.equalsIgnoreCase("numeric") || sqlType.equalsIgnoreCase("real")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("money") || sqlType.equalsIgnoreCase("smallmoney")) {
			return "double";
		} else if (sqlType.equalsIgnoreCase("varchar") || sqlType.equalsIgnoreCase("char") || sqlType.equalsIgnoreCase("nvarchar")
				|| sqlType.equalsIgnoreCase("nchar")) {
			return "String";
		} else if (sqlType.equalsIgnoreCase("datetime")) {
			return "Date";
		}else if (sqlType.equalsIgnoreCase("timestamp")) {
			return "Date";
		}

		else if (sqlType.equalsIgnoreCase("image")) {
			return "Blob";
		} else if (sqlType.equalsIgnoreCase("text")) {
			return "Clob";
		}
		return null;
	}

	/**
	 * 生成Mapper
	 * 
	 */
	public String GenMapper(String packagePath, String tableName) {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + packagePath + ".mapper;\r\n\r\n");
		sb.append("import java.util.List; \r\n\r\n");
		sb.append("import org.apache.ibatis.annotations.Delete;\n");
		sb.append("import org.apache.ibatis.annotations.Insert;\n");
		sb.append("import org.apache.ibatis.annotations.Mapper;\n");
		sb.append("import org.apache.ibatis.annotations.Param;\n");
		sb.append("import org.apache.ibatis.annotations.Result;\n");
		sb.append("import org.apache.ibatis.annotations.Results;\n");
		sb.append("import org.apache.ibatis.annotations.Select;\n");
		sb.append("import org.apache.ibatis.annotations.SelectKey;\n");
		sb.append("import org.apache.ibatis.annotations.Update;\r\n\r\n");
		sb.append("import "+ packagePath +".domain." + initcap(tableName) + ";\r\n\r\n");
		sb.append("@Mapper\n");
		sb.append("public interface " + initcap(tableName) + "Mapper {\r\n\r\n");
		sb.append(result.substring(0, result.length() - 1) + "})\r\n");
		sb.append("\t@Select(\"SELECT * FROM " + tableName + " WHERE id = #{id}\")\n");
		sb.append("\t" + initcap(tableName) + " findById(@Param(\"id\") Integer id);\r\n\r\n");
		sb.append(result.substring(0, result.length() - 1) + "})\r\n");
		sb.append("\t@Select(\"SELECT * FROM " + tableName + "\")\n");
		sb.append("\tList<" + initcap(tableName) + "> findAll();\r\n\r\n");
		sb.append("\t@Insert(\"insert into " + tableName + " (" + insertResult.substring(3, insertResult.length() - 1) + ") values ("
				+ insertValue.substring(6, insertValue.length() - 1) + ")\")\n");
		sb.append("\t@SelectKey(statement=\"SELECT LAST_INSERT_ID()\", keyProperty=\"id\", before=false, resultType=Integer.class)\n");
		sb.append("\tint insert(" + initcap(tableName) + " " + getCamelStr(tableName) + ");\r\n\r\n");
		
		sb.append("\t@Update(\"update " + tableName + " set "+updateResult.substring(9, updateResult.length()-1)+" where id=#{id}\")\n");
		sb.append("\tint update(" + initcap(tableName) + " " + getCamelStr(tableName) + ");\r\n\r\n");
		
		sb.append("\t@Delete(\"delete from " + tableName +" where id=#{id}\")\n");
		sb.append("\tint delete(" + initcap(tableName) + " " + getCamelStr(tableName) + ");\r\n\r\n");
		
		sb.append("\n");
		sb.append("}\n");
		return sb.toString();
	}

	/**
	 * 生成GenController
	 * 
	 */
	public String GenController(String packagePath, String tableName) {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + packagePath + ".web;\r\n\r\n");
		sb.append("import java.util.List;\r\n\r\n");
		sb.append("import io.swagger.annotations.Api;\r\n\r\n");

		sb.append("import org.apache.ibatis.annotations.Param;\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMapping;\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMethod;\n");
		sb.append("import org.springframework.web.bind.annotation.RequestParam;\n");
		sb.append("import org.springframework.web.bind.annotation.RestController;\n");

		sb.append("import " + packagePath + ".domain." + initcap(tableName) + ";\n");
		sb.append("import " + packagePath + ".service." + initcap(tableName) + "Service;\n");
		sb.append("import " + packagePath + ".exception.MyException;\n");
		sb.append("import " + packagePath + ".result.ApiResult;\r\n\r\n");

		sb.append("@Api(\"描述\")\n");
		sb.append("@RestController\n");
		sb.append("@RequestMapping(value = \"/" + getCamelStr(tableName) + "\")\n");
		sb.append("public class " + initcap(tableName) + "Controller {\r\n\r\n");
		sb.append("\t@Autowired\n");
		sb.append("\tprivate " + initcap(tableName) + "Service " + getCamelStr(tableName) + "Service;\r\n\r\n");
		sb.append("\t//@Token\n");

		sb.append("\t//@ApiOperation(value = \"接口描述\", notes = \"\" ,response=" + initcap(tableName) + ".class)\n");
		sb.append("\t@RequestMapping(method = {RequestMethod.POST, RequestMethod.GET},produces = \"application/json\")\n");

		sb.append("\tpublic Object getList(@Param(\"page\") Integer page,@RequestParam(value = \"uuid\", required = false) String uuid) throws MyException {\n");
		sb.append("\t\tList<" + initcap(tableName) + "> r = " + getCamelStr(tableName) + "Service.getAll();\n");
		sb.append("\t\treturn r;\n");
		sb.append("\t}\n");
		sb.append("}\n");
		return sb.toString();
	}

	/**
	 * 生成Service
	 * 
	 */
	public String GenService(String packagePath, String tableName) {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + packagePath + ".service;\r\n\r\n");
		sb.append("import java.util.List;\r\n\r\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\n");
		sb.append("import org.springframework.stereotype.Service;\n");

		sb.append("import " + packagePath + ".domain." + initcap(tableName) + ";\n");
		sb.append("import " + packagePath + ".mapper." + initcap(tableName) + "Mapper;\n");
		sb.append("@Service\n");
		sb.append("public class " + initcap(tableName) + "Service {\r\n\r\n");
		sb.append("\t@Autowired\n");
		sb.append("\tprivate " + initcap(tableName) + "Mapper " + getCamelStr(tableName) + "Mapper;\r\n\r\n");

		sb.append("\t public List<" + initcap(tableName) + "> getAll() {\n");

		sb.append("\t\treturn " + getCamelStr(tableName) + "Mapper.findAll();\n");
		sb.append("\t}\n");
		sb.append("}\n");
		return sb.toString();
	}

	public void wirtFileMapper(String packagePath, String tableName) throws IOException {
		String path = System.getProperty("user.dir") + "/src/main/java/" + packagePath.replaceAll("\\.", "/") + "/mapper";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String resPath = path + "/" + initcap(tableName) + "Mapper.java";
		System.out.println("resPath=" + resPath);
		File f = new File(resPath);
		//if (!f.exists()) {
			FileUtils.writeStringToFile(f, GenMapper(packagePath, tableName));
		//}

	}

	public void wirtFileService(String packagePath, String tableName) throws IOException {
		String path = System.getProperty("user.dir") + "/src/main/java/" + packagePath.replaceAll("\\.", "/") + "/service";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String resPath = path + "/" + initcap(tableName) + "Service.java";
		System.out.println("resPath=" + resPath);
		File f = new File(resPath);
		if (!f.exists()) {
			FileUtils.writeStringToFile(f, GenService(packagePath, tableName));
		}

	}

	public void wirtFileController(String packagePath, String tableName) throws IOException {
		String path = System.getProperty("user.dir") + "/src/main/java/" + packagePath.replaceAll("\\.", "/") + "/web";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		String resPath = path + "/" + initcap(tableName) + "Controller.java";
		System.out.println("resPath=" + resPath);

		File f = new File(resPath);
		if (!f.exists()) {
			FileUtils.writeStringToFile(f, GenController(packagePath, tableName));
		}
	}

}