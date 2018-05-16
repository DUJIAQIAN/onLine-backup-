package cacRead;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ReadCAC {

	public class market{
		private String date;
		private Double value;
		
		public String getDate() {
			return this.date;
			
		}
		public Double getValue() {
			return this.value;
			
		}
		public void setDate(String cellValue) {
			this.date=cellValue;
			
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
	
	public List<market> readMarket(String excelFilePath3)throws IOException{
		List<market> list3 = new ArrayList<>();
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath3));
		
		Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);
        Iterator<Row> iterator = firstSheet.iterator();
        
        while (iterator.hasNext()) {
            Row nextColumn = iterator.next();
            Iterator<Cell> cellIterator = nextColumn.cellIterator();
            market aMarket = new market();
     
            while (cellIterator.hasNext()) {
                Cell nextCell = cellIterator.next();
                int rowIndex = nextCell.getRowIndex();
     
                switch (nextCell.getColumnIndex()) {
                    case 1:
                    	DataFormatter formatter = new DataFormatter();
                        aMarket.setDate(formatter.formatCellValue(nextCell));
                        break;
                    case 2:
                    	aMarket.setValue(nextCell.getNumericCellValue());
                        break;
                }
                }

            list3.add(aMarket);
        }
        workbook.close();
        inputStream.close();
        
		return list3;
	}
}
	

