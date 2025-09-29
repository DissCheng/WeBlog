-- KEYS[1]   : ZSET 键
-- KEYS[2]   : STRING 键
-- ARGV[1]   : score
-- ARGV[2]   : member
if redis.call('EXISTS', KEYS[1]) == 0 then
    return 0        -- 键不存在，拒绝插入
end
-- 键存在，再执行 ZADD（允许覆盖 score；如想禁止覆盖可加 NX）
redis.call('ZADD', KEYS[1], ARGV[1], ARGV[2])
redis.call('SET' , KEYS[2], 0)
return 1