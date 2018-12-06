package william.sundial.server.processor;

import william.sundial.common.dto.TaskInfoDto;

import java.util.List;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/22 13:33
 * @Description:
 */
public interface RemoteTaskScheduler {
    void addNewTasks(List<TaskInfoDto> taskInfoDtos);
}
