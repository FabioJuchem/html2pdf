import axios from 'axios';

export default class Html2PdfService {
  public static getPdf(): Promise<any> {
    return axios.get('/api/getpdf', { responseType: 'arraybuffer' })
      .then((response: any) => response);
  }
}
