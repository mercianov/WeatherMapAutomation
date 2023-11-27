package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name = "CityName")
    public String[][] getCityNamesData() throws IOException{
        String path=System.getProperty("user.dir")+"//testData//WeatherData.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum=xl.getRowCount("Sheet1");
        int colcount=xl.getCellCount("Sheet1",1);
        String apidata[][]=new String[rownum][colcount];
        for(int i=1; i<=rownum;i++){
            for(int j=0;j<colcount;j++){
                apidata[i-1][j]=xl.getCellData("Sheet1",i,j);
            }
        }
        return apidata;
    }

    @DataProvider(name="Coordinate")
    public String[][] getCoordinateData() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//WeatherData.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum=xl.getRowCount("Sheet2");
        int colcount=xl.getCellCount("Sheet2",1);
        String apidata[][]=new String[rownum][colcount];
        for(int i=1; i<=rownum;i++){
            for(int j=0;j<colcount;j++){
                apidata[i-1][j]=xl.getCellData("Sheet2",i,j);
                System.out.println(apidata[i-1][j]);
            }
        }
        return apidata;
    }
    @DataProvider(name = "CityID")
    public String[][] getCityIdData() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//WeatherData.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum=xl.getRowCount("Sheet3");
        int colcount=xl.getCellCount("Sheet3",1);
        String apidata[][]=new String[rownum][colcount];
        for(int i=1; i<=rownum;i++){
            for(int j=0;j<colcount;j++){
                apidata[i-1][j]=xl.getCellData("Sheet3",i,j);
            }
        }
        return apidata;
    }
    @DataProvider(name = "ZipCode")
    public String[][] getZipCodeData() throws IOException
    {
        String path=System.getProperty("user.dir")+"//testData//WeatherData.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum=xl.getRowCount("Sheet4");
        int colcount=xl.getCellCount("Sheet4",1);
        String apidata[][]=new String[rownum][colcount];
        for(int i=1; i<=rownum;i++){
            for(int j=0;j<colcount;j++){
                apidata[i-1][j]=xl.getCellData("Sheet4",i,j);
                System.out.println(apidata[i-1][j]);
            }
        }
        return apidata;
    }
}
