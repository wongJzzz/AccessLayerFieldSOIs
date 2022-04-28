package entsdkaccess.soi;

import com.esri.arcgis.carto.IMapLayerInfo;
import com.esri.arcgis.carto.IMapLayerInfos;
import com.esri.arcgis.carto.IMapServer;
import com.esri.arcgis.carto.IMapServerInfo;
import com.esri.arcgis.system.*;

import java.io.IOException;
import java.util.Arrays;


public class SOIUtil    {
    private ILog logger;

    public SOIUtil()
    {
        this.logger = ServerUtilities.getServerLogger();
    }

    /**
     * This method returns the layer id of the layer matching the specified name
     */
    public int getLayerIdByName(IMapServer mapService, String name) throws IOException {
        IMapServerInfo msInfo = mapService.getServerInfo(mapService.getDefaultMapName());
        IMapLayerInfos layerInfos = msInfo.getMapLayerInfos();

        for (int i = 0; i < layerInfos.getCount(); i++)
        {
            IMapLayerInfo layerInfo = layerInfos.getElement(i);
            if (layerInfo.getName().equalsIgnoreCase(name))
            {
                return layerInfo.getID();
            }
        }

        return -1;
    }

    public String getLayerIdStringByNameListString(IMapServer mapService, String nameListString) throws IOException{
        IMapServerInfo msInfo = mapService.getServerInfo(mapService.getDefaultMapName());
        IMapLayerInfos layerInfos = msInfo.getMapLayerInfos();
        StringBuilder idListString = new StringBuilder();
        int layerCount = 0;

        for(int i = 0; i < layerInfos.getCount(); i++){
            IMapLayerInfo layerInfo = layerInfos.getElement(i);
            String layerName = layerInfo.getName();
            if(nameListString.contains(layerName)){
                idListString.append(layerInfo.getID()).append(",");
                layerCount++;
            }

        }
        if(layerCount > 0){
            return idListString.substring(0,idListString.length()-1);
        }
        return null;
    }
}
