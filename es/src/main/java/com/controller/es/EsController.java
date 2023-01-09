package com.controller.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import com.entity.AppUser;
import com.service.AppUserDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @author THT
 */
@RestController
public class EsController {
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(EsController.class);
    @Resource
    private ElasticsearchClient template;

    @Autowired
    private AppUserDao userDao;

    @RequestMapping("/importAll")
    public void importAll() throws IOException {

        AppUser appUser = new AppUser();
//        Function<AppUser, List<AppUser>> appUserList = userDao::appUserList;
//        appUserList.apply(appUser);

        List<AppUser> appUsers = userDao.appUserList(appUser);

        //List<IndexQuery> queries = appUsers.stream().map(appuser -> {
        //    IndexQuery indexQuery = new IndexQuery();
        //    indexQuery.setId(appuser.getId() + "");
        //    indexQuery.setObject(appuser);
        //    return indexQuery;
        //}).collect(Collectors.toList());
        //template.bulkIndex(queries, IndexCoordinates.of("app_user"));
        List<BulkOperation> bulkOperations = new ArrayList<>();
        //将user中id作为es id，也可不指定id es会自动生成id
        appUsers.forEach(a -> bulkOperations
                .add(BulkOperation.of(b -> b.index(c -> c.id(a.getId().toString()).document(a)))));
        template.bulk(x ->x.index("user").operations(bulkOperations));

    }

//    @RequestMapping("/selectAll")
//    public SearchHits selectAll(String id){
//        Query query = template.matchAllQuery();
////        IndexQuery indexQuery = new IndexQuery();
////        indexQuery.setId("1");
////        template.doIndex(indexQuery, IndexCoordinates.of("app_user"));
//        AppUser appUser = template.get(id, AppUser.class);
//        Query query1 = template.matchAllQuery();
//        SearchHits<AppUser> search = template.search(query1, AppUser.class);
////        List<MultiGetItem<AppUser>> multiGetItems = template.multiGet(query1, AppUser.class, IndexCoordinates.of(
////                "app_user"));
//        return search;
//    }

    public static void main(String[] args) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("1", "11");
        String s = hashMap.computeIfAbsent("2", (item) ->
                Integer.parseInt(item) + 1 + "");
        System.out.println("s = " + s);
        System.out.println("hashMap = " + hashMap);

        String s1 = hashMap.computeIfPresent("1", (item1, item2) -> {
            return item1 + item2;
        });
        System.out.println("s1 = " + s1);
        System.out.println("hashMap = " + hashMap);
        a();
    }

    public static void a(){
        HashMap<Integer, BitSet> map = new HashMap<>();
        map.computeIfAbsent(135, k -> new BitSet()).set(66789098);
        Set<Map.Entry<Integer, BitSet>> entries = map.entrySet();
        map.forEach((x, y) -> {
            String s = x.toString() + y.toString();
            System.out.println(s);
        });
        String join = StringUtils.join(map,",");
        System.out.println(join);
//        System.out.println("map = " + map.get(135).toString());
    }
}
