local result = {}	--返回的变量

local key = KEYS[1]
local status = redis.call('get',key)
local jsonRedisTemp={}
if status == "idempotent-biz-process" then
    jsonRedisTemp["result"] = "idempotent-biz-process"
	result[1] = cjson.encode(jsonRedisTemp)
	return result
elseif status ~= "idempotent-biz-error" then
	redis.call('del',key)
	jsonRedisTemp["result"] = "success"
    result[1] = cjson.encode(jsonRedisTemp)
    return result
else
    jsonRedisTemp["result"] = "success"
   	result[1] = cjson.encode(jsonRedisTemp)
    return result
end