package flaskoski.faire.apicommunication.UpdateOptionStrategy;

import flaskoski.faire.model.Option;

/**
 * Strategy for updating option after checking if order will be or not processed
 */
public class UpdateOptionDontProcess implements IUpdateOption {
    @Override
    public void setOption(Option option, Integer quantity) {
        option.setActive(false);
    }
}