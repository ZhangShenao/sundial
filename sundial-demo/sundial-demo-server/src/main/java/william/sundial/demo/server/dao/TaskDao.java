package william.sundial.demo.server.dao;

import william.mybatis.annotation.AutoResult;
import william.sundial.demo.server.entity.TaskEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 14:04
 * @Description:
 */
@Repository
@AutoResult(TaskEntity.class)
@Mapper
public interface TaskDao{
    @Insert(" INSERT INTO `sundial_task` (`task_name`, `task_key`, `task_type`, `service_name`, `execution_status`)" +
            " VALUES (#{entity.taskName}, #{entity.taskKey}, #{entity.taskType}, #{entity.serviceName}, #{entity.executionStatus})")
    int insert(@Param("entity") TaskEntity entity);

    @Update(" UPDATE `sundial_task` set `task_name` = #{entity.taskName}, `task_key` = #{entity.taskKey}, `task_type` = #{entity.taskType}, `service_name` = #{entity.serviceName}, `execution_status` = #{entity.executionStatus}," +
            " `last_execution_result` = #{entity.lastExecutionResult}, `last_finish_time` = #{entity.lastFinishTime}" +
            " WHERE `id` = #{entity.id}")
    void update(@Param("entity") TaskEntity entity);

    @Select("select * from sundial_task where task_key = #{taskKey}")
    @ResultMap("TaskEntity")
    TaskEntity findByKey(@Param("taskKey") String taskKey);
}
