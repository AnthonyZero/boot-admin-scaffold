package com.anthonyzero.scaffold;

import com.anthonyzero.scaffold.common.core.RequestQuery;
import com.anthonyzero.scaffold.common.core.SysConstant;
import com.anthonyzero.scaffold.common.utils.SortUtil;
import com.anthonyzero.scaffold.system.entity.User;
import com.anthonyzero.scaffold.system.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplicationTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void test() {
        RequestQuery query = new RequestQuery();
        query.setPageNum(1);
        query.setPageSize(1);
        /*query.setField("userId");
        query.setOrder("asc");*/
        Page<User> page = new Page<>(query.getPageNum(), query.getPageSize());
        SortUtil.handlePageSort(query, page, "createTime", SysConstant.ORDER_ASC);
        User user = new User();
        user.setNickname("大");
        user.setStatus("1");
        IPage<User> userIPage = userMapper.selectPageUser(page, user);
        System.out.println("数据：" + userIPage.getPages() + " - " +  userIPage.getTotal());
        userIPage.getRecords().forEach(c -> System.out.println(c));
    }
}
