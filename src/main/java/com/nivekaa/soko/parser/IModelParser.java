package com.nivekaa.soko.parser;

import java.util.List;

/**
 * @author nivekaa
 * Created 22/03/2020 at 17:07
 * Class com.nivekaa.soko.parser.IModelParser
 */

public interface IModelParser<C> {
    C toModel(String json);
    List<C> toListModel(String json);
}
