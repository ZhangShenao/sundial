package william.sundial.core.command;

import william.sundial.common.dto.TaskInfoDto;
import william.sundial.core.payload.RegisterTasksCommandTransportPayload;
import william.sundial.core.task.definition.RemoteTask;
import william.sundial.core.utils.TaskDataConverter;
import org.springframework.util.CollectionUtils;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/11/13 17:15
 * @Description:
 */
public class RegisterTasksCommand implements RemoteCommand<RegisterTasksCommandTransportPayload>{
    private Collection<RemoteTask> remoteTasks;

    public RegisterTasksCommand(Collection<RemoteTask> remoteTasks) {
        this.remoteTasks = remoteTasks;
    }

    public Collection<RemoteTask> getRemoteTasks() {
        return remoteTasks;
    }

    public void setRemoteTasks(Collection<RemoteTask> remoteTasks) {
        this.remoteTasks = remoteTasks;
    }

    @Override
    public RegisterTasksCommandTransportPayload parseTransportPayload() {
        if (CollectionUtils.isEmpty(remoteTasks)){
            return null;
        }

        List<TaskInfoDto> taskInfos = remoteTasks.stream()
                .map(remoteTask -> TaskDataConverter.taskInstance2Dto(remoteTask))
                .collect(Collectors.toList());

        return RegisterTasksCommandTransportPayload.valueOf(taskInfos);
    }
}
