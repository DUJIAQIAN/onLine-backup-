package net.codejava.excel;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
 
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class readstock {
	
	public class stock{
		private String name;
		private Double value;
	//getters
		public String getName() {
			return this.name;
			
		}
		public Double getValue() {
			return this.value;
			
		}
   //setters
		public void setName(String cellValue) {
			this.name=cellValue;
			
		}
		public void setValue(Double cellValue) {
			this.value=cellValue;
			
		}
	}
	
    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_STRING:
            return cell.getStringCellValue();
     
        case Cell.CELL_TYPE_BOOLEAN:
            return cell.getBooleanCellValue();
     
        case Cell.CELL_TYPE_NUMERIC:
            return cell.getNumericCellValue();
        }
     
        return null;
    }
	
	public List<stock> readStocks(String excelFilePath4)throws IOException{
		List<stock> list4 = new ArrayList<>();
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath4));
		
		Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        
        while (iterator.hasNext()) {
            Row nextRow = iterator.next();
            Iterator<Cell> cellIterator = nextRow.cellIterator();
            stock aStock = new stock();
     
            while (cellIterator.hasNext()) {
            	
                Cell nextCell = cellIterator.next();
                int columnIndex = nextCell.getRowIndex();
                
 
                switch (nextCell.getColumnIndex()) {
                    case 0:
                        aStock.setName(nextCell.getStringCellValue());
                        break;
                    case 6:
                    	try{
                    		aStock.setValue(nextCell.getNumericCellValue());
                    	}catch(Exception ex) {
                    	}
                        break;
                }
                }
            

            list4.add(aStock);
        }
        workbook.close();
        inputStream.close();
        
		return list4;
	}
}
