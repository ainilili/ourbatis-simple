//import java.io.IOException;  
//import java.lang.reflect.Field;  
//import java.util.ArrayList;
//import java.util.HashMap;  
//import java.util.List;
//import java.util.Map;  
//import java.util.Set;  
//  
//import org.apache.commons.logging.Log;  
//import org.apache.commons.logging.LogFactory;  
//import org.apache.ibatis.builder.xml.XMLMapperBuilder;  
//import org.apache.ibatis.session.Configuration;  
//import org.apache.ibatis.session.SqlSessionFactory;  
//import org.springframework.core.io.Resource;  
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;  
//  
//public class RefreshMapperCache {  
//    private Log log  = LogFactory.getLog(RefreshMapperCache.class);  
//    
//    private SqlSessionFactory sqlSessionFactory;  
//    private Resource[] mapperLocations;  
//    private String packageSearchPath;  
//    private HashMap<String, Long> fileMapping = new HashMap<String, Long>();// 记录文件是否变化  
//    
//    //记录发生改变的xml文件名称
//    private List<String> changeResourceNameList = new ArrayList<>();
//      
//    public void refreshMapper() {  
//        try {  
//            Configuration configuration = this.sqlSessionFactory.getConfiguration();  
//              
//            // step.1 扫描文件  
//            try {  
//                this.scanMapperXml();  
//            } catch (IOException e) {  
//                log.error("packageSearchPath扫描包路径配置错误");  
//                return;  
//            }  
//              
////            System.out.println("==============刷新前mapper中的内容 start===============");  
////            //获取xml中的每个语句的名称即 id = "findUserById";
////            for (String name : configuration.getMappedstatusmentNames()) {
////                System.out.println(name);  
////            }  
////            System.out.println("==============刷新前mapper中的内容   end===============");  
//            
//            //清空被修改过后的文件名称，确保该集合是空的
//            changeResourceNameList.clear();
//            // step.2 判断是否有文件发生了变化  
//            if (this.isChanged()) {  
//                // step.2.1 清理  
//                this.removeConfig(configuration);  
//  
//                // step.2.2 重新加载  
//                for (Resource configLocation : mapperLocations) {  
//                    try { 
//                    	//匹配被修改过的mapper文件，如果存在，则重新加载
//                    	//如果想要重新加载全部mapper，可以不匹配
//                    	if(changeResourceNameList.contains(configLocation.getFilename())){
//                    		 XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configLocation.getInputStream(), configuration, configLocation.toString(), configuration.getSqlFragments());  
//                             xmlMapperBuilder.parse();  
//                             System.out.println("mapper文件[" + configLocation.getFilename() + "]缓存加载成功");  
//                    	}
//                    } catch (IOException e) {  
//                    	System.out.println("mapper文件[" + configLocation.getFilename() + "]不存在或内容格式不对");  
//                        continue;  
//                    }  
//                }
//                //清空被修改过后的文件名称
//                changeResourceNameList.clear();
//            }  
//              
////            System.out.println("--------------------------刷新后mapper中的内容 start--------------------------");  
////            for (String name : configuration.getMappedstatusmentNames()) {
////                System.out.println(name);  
////            }
////            System.out.println("--------------------------刷新后mapper中的内容  end--------------------------");   
//        } catch (Exception e) {  
//           System.out.println("****************刷新缓存异常： "+e.getMessage());
//        }  
//    }  
//      
//    public void setPackageSearchPath(String packageSearchPath) {  
//        this.packageSearchPath = packageSearchPath;  
//    }  
//      
//    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {  
//        this.sqlSessionFactory = sqlSessionFactory;  
//    }  
//  
//    /** 
//     * 扫描xml文件所在的路径 
//     * @throws IOException  
//     */  
//    private void scanMapperXml() throws IOException {
//        this.mapperLocations = new PathMatchingResourcePatternResolver().getResources(packageSearchPath);  
//    }  
//  
//    /** 
//     * 清空Configuration中几个重要的缓存 
//     * @param configuration 
//     * @throws Exception 
//     */  
//    private void removeConfig(Configuration configuration) throws Exception {  
//        Class<?> classConfig = configuration.getClass();  
//        clearMap(classConfig, configuration, "mappedstatusments");
//        clearMap(classConfig, configuration, "caches");  
//        clearMap(classConfig, configuration, "resultMaps");  
//        clearMap(classConfig, configuration, "parameterMaps");  
//        clearMap(classConfig, configuration, "keyGenerators");  
//        clearMap(classConfig, configuration, "sqlFragments");  
//  
//        clearSet(classConfig, configuration, "loadedResources");  
//  
//    }  
//  
//    @SuppressWarnings("rawtypes")  
//    private void clearMap(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {  
//        Field field = classConfig.getDeclaredField(fieldName);  
//        field.setAccessible(true);  
//        Map mapConfig = (Map) field.get(configuration);  
//        mapConfig.clear();  
//    }  
//  
//    @SuppressWarnings("rawtypes")  
//    private void clearSet(Class<?> classConfig, Configuration configuration, String fieldName) throws Exception {  
//        Field field = classConfig.getDeclaredField(fieldName);  
//        field.setAccessible(true);  
//        Set setConfig = (Set) field.get(configuration);  
//        setConfig.clear();  
//    }  
//      
//    /** 
//     * 判断文件是否发生了变化 
//     * @param resource 
//     * @return 
//     * @throws IOException 
//     */  
//    private boolean isChanged() throws IOException {  
//        boolean flag = false;  
//        System.out.println("***************************获取文件名   开始************************************");
//        for (Resource resource : mapperLocations) {  
//            String resourceName = resource.getFilename();  
//            
//            System.out.println("resourceName == " + resourceName+",  path = "+resource.getURL().getPath());
//            
//            boolean addFlag = !fileMapping.containsKey(resourceName);// 此为新增标识  
//              
//            // 修改文件:判断文件内容是否有变化  
//            Long compareFrame = fileMapping.get(resourceName);  
//            long lastFrame = resource.contentLength() + resource.lastModified();  
//            boolean modifyFlag = null != compareFrame && compareFrame.longValue() != lastFrame;// 此为修改标识  
//            
//            if(addFlag){
//            	System.out.println("            新增了：==="+ resourceName);
//            }
//            if(modifyFlag){
//            	System.out.println("            修改了：==="+ resourceName);
//            }
//            
//            // 新增或是修改时,存储文件  
//            if(addFlag || modifyFlag) {  
//                fileMapping.put(resourceName, Long.valueOf(lastFrame));// 文件内容帧值  
//                flag = true;  
//                changeResourceNameList.add(resourceName);
//            }  
//        } 
//        System.out.println("***************************获取文件名   结束************************************");
//        return flag;  
//    }  
//}  
