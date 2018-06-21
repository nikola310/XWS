package xmlweb.projekat.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xmlweb.projekat.model.Accomodation;
import xmlweb.projekat.model.Comment;
import xmlweb.projekat.model.User;
import xmlweb.projekat.model.dtos.CommentDTO;
import xmlweb.projekat.repository.AccomodationRepository;
import xmlweb.projekat.repository.CommentRepository;
import xmlweb.projekat.repository.UserRepository;
import xmlweb.projekat.service.interfaces.CommentServiceInterface;

/**
 * @author Nikola
 *
 */
@Service
@Transactional
public class CommentService implements CommentServiceInterface {

	private CommentRepository repository;

	private AccomodationRepository accomodationRepo;

	private UserRepository userRepo;

	@Autowired
	public CommentService(CommentRepository repository, AccomodationRepository accomodationRepo,
			UserRepository userRepo) {
		super();
		this.repository = repository;
		this.accomodationRepo = accomodationRepo;
		this.userRepo = userRepo;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xmlweb.projekat.service.interfaces.ServiceInterface#Create(java.lang.Object)
	 */
	@Override
	public boolean Create(CommentDTO dto) {
		try {
			ModelMapper mapper = new ModelMapper();
			Comment comm = mapper.map(dto, Comment.class);
			Accomodation acc = accomodationRepo.getOne(dto.getAccomodation());
			User author = userRepo.getOne(dto.getAuthor());
			comm.setAccomodation(acc);
			comm.setAuthor(author);

			repository.save(comm);

			return true;
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see xmlweb.projekat.service.interfaces.ServiceInterface#Read(long)
	 */
	@Override
	public CommentDTO Read(long id) {
		try {
			Comment tmp = repository.getOne(id);
			CommentDTO dto = new CommentDTO(tmp.getId(), tmp.getContent(), tmp.getRating(), tmp.isApproved(),
					tmp.getAccomodation().getId(), tmp.getVersion(), tmp.getAuthor().getId());

			dto.setAccomodationName(tmp.getAccomodation().getAccomodationName());
			dto.setAuthorName(tmp.getAuthor().getUserName());

			return dto;
		} catch (Exception exc) {
			exc.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see xmlweb.projekat.service.interfaces.ServiceInterface#ReadAll()
	 */
	@Override
	public ArrayList<CommentDTO> ReadAll() {
		ArrayList<Comment> listEntities = (ArrayList<Comment>) repository.findAll();
		ArrayList<CommentDTO> listDTO = new ArrayList<CommentDTO>();
		for (Comment tmp : listEntities) {
			try {
				CommentDTO dto = new CommentDTO(tmp.getId(), tmp.getContent(), tmp.getRating(), tmp.isApproved(),
						tmp.getAccomodation().getId(), tmp.getVersion(), tmp.getAuthor().getId());

				dto.setAccomodationName(tmp.getAccomodation().getAccomodationName());
				dto.setAuthorName(tmp.getAuthor().getUserName());

				listDTO.add(dto);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * xmlweb.projekat.service.interfaces.ServiceInterface#Update(java.lang.Object)
	 */
	@Override
	public boolean Update(CommentDTO dto) {
		Comment toUpdate = repository.getOne(dto.getId());

		try {
			if (toUpdate.getVersion() != dto.getVersion()) {
				return false;
			}
			toUpdate.setContent(dto.getContent());
			toUpdate.setRating(dto.getRating());
			repository.save(toUpdate);

		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see xmlweb.projekat.service.interfaces.ServiceInterface#Delete(long)
	 */
	@Override
	public boolean Delete(long id) {
		try {
			repository.deleteById(id);
		} catch (Exception exc) {
			exc.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<CommentDTO> getNotManaged() {
		ArrayList<Comment> listEntities = (ArrayList<Comment>) repository.getCommentByApprovedIsNull();
		ArrayList<CommentDTO> listDTO = new ArrayList<CommentDTO>();
		for (Comment tmp : listEntities) {
			try {
				CommentDTO dto = new CommentDTO(tmp.getId(), tmp.getContent(), tmp.getRating(), tmp.isApproved(),
						tmp.getAccomodation().getId(), tmp.getVersion(), tmp.getAuthor().getId());

				dto.setAccomodationName(tmp.getAccomodation().getAccomodationName());
				dto.setAuthorName(tmp.getAuthor().getUserName());

				listDTO.add(dto);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

		return listDTO;
	}

	@Override
	public boolean acceptComment(long id, int version) {
		try {
			Comment comm = repository.getOne(id);

			if (version != comm.getVersion()) {
				return false;
			}

			comm.setApproved(true);

			repository.save(comm);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean rejectComment(long id, int version) {
		try {
			Comment comm = repository.getOne(id);

			if (version != comm.getVersion()) {
				return false;
			}

			comm.setApproved(false);

			repository.save(comm);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
