package com.liyulin.demo.mybatis.plus.test;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liyulin.demo.mybatis.plus.biz.TestBiz;
import com.liyulin.demo.mybatis.plus.biz.TradeReqBiz;
import com.liyulin.demo.mybatis.plus.entity.TestEntity;
import com.liyulin.demo.mybatis.plus.entity.TradeReqEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TradeReqTest {

    @Autowired
    private TradeReqBiz tradeReqBiz;

    @Test
    public void testInsert() {
        long t1 = System.currentTimeMillis();
        for (int k = 0; k < 1000; k++) {
            List<TradeReqEntity> data = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                TradeReqEntity tradeReq = new TradeReqEntity();
                tradeReq.setUserId((long) i);
                tradeReq.setUserIdShort((long) i);
                tradeReq.setFundAccount(String.format("123%s", i));
                tradeReq.setAccountBusinessType(1);
                tradeReq.setPartnerTradeNo("123421312312312");
                tradeReq.setTradeType(100);
                tradeReq.setTradeSubType("90");
                tradeReq.setBusinessId(1234L);
                tradeReq.setMoneyType("USD");
                tradeReq.setExchangeType("US");
                tradeReq.setTradeStatus(20);
                tradeReq.setTranDate(LocalDate.now());
                tradeReq.setBusinessTime(new Date());
                tradeReq.setReqUrl("https://szwiki.yxzq.com/pages/viewpage.action?pageId=63838118");
                tradeReq.setReqData("{\"accountBusinessType\":1,\"calNight\":true,\"moneyType\":\"us\"}");
                tradeReq.setRespData("{\"code\":1,\"data\":{\"appHoldAssets\":[{\"appCardType\":1,\"businessStatusMap\":{},\"exchangeType\":\"\",\"marketValue\":1,\"moneyType\":\"\",\"todayProfit\":1,\"totalHoldingBalance\":1,\"underlyingCount\":1,\"underlyingPositions\":[{\"lastPrice\":1,\"marketValue\":1,\"positionPnl\":1,\"strategyHoldInfos\":[{\"holdInfos\":[{\"accountBusinessType\":1,\"assetKind\":1,\"currentAmount\":1,\"enableAmount\":1,\"exchangeRate\":1,\"exchangeType\":\"\",\"frontStatus\":1,\"holdingBalance\":1,\"holdingBalancePercent\":1,\"holdingPercent\":1,\"lastPrice\":1,\"marketValue\":1,\"moneyType\":\"\",\"multiplier\":1,\"needShowPreTodayProfit\":true,\"preMarketValue\":1,\"preTodayProfit\":1,\"sessionType\":1,\"startDate\":\"\",\"startPrice\":1,\"stockCode\":\"\",\"stockName\":\"\",\"stockType\":\"\",\"todayDeltaIn\":1,\"todayDeltaOut\":1,\"todayProfit\":1,\"todayProfitPercent\":1,\"todayProfitVar\":1,\"underlyingName\":\"\",\"underlyingSymbol\":\"\"}],\"lastPrice\":1,\"marketValue\":1,\"portfolioStrategy\":true,\"positionPnl\":1,\"strategyName\":\"\"}],\"underlyingName\":\"\",\"underlyingSymbol\":\"\"}]}],\"assetSingleInfoRespVOS\":[{\"appCardType\":1,\"businessStatusMap\":{},\"exchangeType\":\"\",\"getStartDate\":{},\"holdInfos\":[{\"accountBusinessType\":1,\"assetKind\":1,\"currentAmount\":1,\"enableAmount\":1,\"exchangeRate\":1,\"exchangeType\":\"\",\"frontStatus\":1,\"holdingBalance\":1,\"holdingBalancePercent\":1,\"holdingPercent\":1,\"lastPrice\":1,\"marketValue\":1,\"moneyType\":\"\",\"multiplier\":1,\"needShowPreTodayProfit\":true,\"preMarketValue\":1,\"preTodayProfit\":1,\"sessionType\":1,\"startDate\":\"\",\"startPrice\":1,\"stockCode\":\"\",\"stockName\":\"\",\"stockType\":\"\",\"todayDeltaIn\":1,\"todayDeltaOut\":1,\"todayProfit\":1,\"todayProfitPercent\":1,\"todayProfitVar\":1,\"underlyingName\":\"\",\"underlyingSymbol\":\"\"}],\"marketValue\":1,\"moneyType\":\"\",\"todayProfit\":1,\"totalHoldingBalance\":1}],\"totalData\":{\"asset\":1,\"availableBalance\":1,\"availableBalanceList\":[{\"availableBalance\":1,\"moneyType\":\"\"}],\"cashBalance\":1,\"debitBalance\":1,\"dueInterest\":1,\"marketValue\":1,\"moneyType\":\"\",\"mv\":1,\"otherAsset\":1,\"purchasePower\":1,\"riskCode\":1,\"riskCodeName\":\"\"},\"userId\":1},\"error\":\"error info\",\"msg\":\"\"}");
                tradeReq.setRemark("test");
                tradeReq.setVersion(1);
                tradeReq.setCreateTime(new Date());
                tradeReq.setUpdateTime(new Date());

                data.add(tradeReq);
            }
            tradeReqBiz.saveBatch(data);
        }
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

}