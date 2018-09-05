package com.medical.filter;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.ehcache.EhCacheManager;

/**
 * @desc 登录限制 5 次
 * @author apple
 */

public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher {
    private static final Logger logger = Logger.getLogger(RetryLimitCredentialsMatcher.class);
    // 集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发

    private EhCacheManager ehcacheManager;
    private int countMax = 5;
    private String retryCacheName;

    public RetryLimitCredentialsMatcher(EhCacheManager cacheManager) {
        this.ehcacheManager = cacheManager;
    }

    public void setMaxRetryNum(int max) {
        this.countMax = max;
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        Cache<String, AtomicInteger> passwordRetryCache = ehcacheManager.getCache("passwordRetryCache");
        String username = (String) token.getPrincipal();
        // retry count + 1
        AtomicInteger retryCount = passwordRetryCache.get(username);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > 5) {
            logger.warn("username: " + username + " tried to login more than 5 times in period");
            throw new ExcessiveAttemptsException(
                    "username: " + username + " tried to login more than 5 times in period");
        }
        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            // clear retry data
            passwordRetryCache.remove(username);
        }
        return matches;
    }

}
