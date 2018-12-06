package william.sundial.core.command;

import william.sundial.common.dto.TaskInfoDto;
import william.sundial.common.dto.TaskResultDto;
import william.sundial.core.payload.RecordTaskResultCommandTransportPayload;
import william.sundial.core.task.definition.RemoteTask;
import william.sundial.core.utils.TaskDataConverter;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/10/24 16:31
 * @Description:
 */
public class RecordTaskResultCommand implements RemoteCommand<RecordTaskResultCommandTransportPayload> {
    private RemoteTask remoteTask;
    private TaskResultDto resultDto;

    public RemoteTask getRemoteTask() {
        return remoteTask;
    }

    public void setRemoteTask(RemoteTask remoteTask) {
        this.remoteTask = remoteTask;
    }

    public TaskResultDto getResultDto() {
        return resultDto;
    }

    public void setResultDto(TaskResultDto resultDto) {
        this.resultDto = resultDto;
    }

    @Override
    public RecordTaskResultCommandTransportPayload parseTransportPayload() {
        TaskInfoDto taskInfoDto = TaskDataConverter.taskInstance2Dto(remoteTask);
        return RecordTaskResultCommandTransportPayload.valueOf(taskInfoDto,resultDto);
    }
}
